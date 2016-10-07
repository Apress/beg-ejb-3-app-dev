package com.apress.ejb3.chapter07.business.mdb;

import com.apress.ejb3.chapter07.business.PurchaseOrder;

import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;

import com.apress.ejb3.wineapp.InventoryItem;
import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;

import javax.annotation.Resource;

import javax.ejb.MessageDriven;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "PurchaseOrderTopic")
public class

OrderProcessingMDBBean
  implements MessageListener
{

  private PurchaseOrder po;

  @PersistenceContext(unitName = "wineStoreUnit")
  private EntityManager em;

  @Resource(mappedName = "StatusMessageTopicConnectionFactory")
  private TopicConnectionFactory statusMessageTopicCF;

  @Resource(mappedName = "StatusMessageTopic")
  private Topic statusTopic;

  public void onMessage(Message message) {
    try {
      if (message instanceof ObjectMessage) {
        ObjectMessage objMessage = (ObjectMessage)message;
        Object obj = objMessage.getObject();
        if (obj instanceof PurchaseOrder) {
          po = (PurchaseOrder)obj;
          processOrder(po);
        }
      }
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
  }

  private void processOrder(PurchaseOrder po) {
    Individual customer = 
      (Individual)em.createNamedQuery("findCustomerById").setParameter("id", 
                                                                       po.getCustomerId()).getSingleResult();
    CustomerOrder order = po.getCustomerOrder();
    em.persist(order);
    customer.addCustomerOrder(order);
    customer = em.merge(customer);
    for (OrderItem oItem: order.getOrderItemCollection()) {

      Wine wine = oItem.getWine();
      Long qty = oItem.getQuantity();
      deductInventory(wine, qty);
    }

    String from = "EJB3-WineApplication";
    String to = customer.getEmail();
    String content = 
      "Your order has been processed " + "If you have questions" + 
      " call Beginning EJB 3 Wine Store Application with order id # " + 
      po.getCustomerOrder().getId().toString();
    sendStatus(from, to, content);

  }

  private void deductInventory(Wine tempWine, Long deductQty) {
    InventoryItem iItem = 
      (InventoryItem)em.createNamedQuery("findInventoryItemByWine").setParameter("wine", 
                                                                                 tempWine).getSingleResult();
    Long newQty = iItem.getQuantity() - deductQty;
    iItem.setQuantity(newQty);
    em.merge(iItem);
  }

  private void sendStatus(String from, String to, String content) {
    try {
      Connection connection = statusMessageTopicCF.createConnection();
      connection.start();
      Session session = 
        connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      MessageProducer producer = session.createProducer(statusTopic);
      MapMessage message = session.createMapMessage();
      message.setStringProperty("from", from);
      message.setStringProperty("to", to);
      message.setStringProperty("subject", "Status of your wine order");
      message.setStringProperty("content", content);
      producer.send(message);
      producer.close();
      session.close();
      connection.close();
    }
    catch (JMSException e) {
      e.printStackTrace();
    }

  }
}
