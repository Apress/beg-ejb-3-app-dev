package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.credit.CreditCheckEndpointBean;
import com.apress.ejb3.credit.CreditService;
import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.CustomerOrder;

import com.apress.ejb3.wineapp.Individual;

import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.Stateless;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.xml.ws.WebServiceRef;

@Stateless(name = "OrderProcessFacade")
public class OrderProcessFacadeBean
  implements OrderProcessFacade, OrderProcessFacadeLocal
{
  @PersistenceContext(unitName = "wineStoreUnit")
  private EntityManager em;

  @Resource(mappedName = "poTopicConnectionFactory")
  private TopicConnectionFactory poTopicCF;

  @Resource(mappedName = "PurchaseOrderTopic")
  private Topic poTopic;

  @WebServiceRef(type = CreditService.class)
  CreditService service;

  public OrderProcessFacadeBean() {
  }

  public Object mergeEntity(Object entity) {
    return em.merge(entity);
  }

  public Object persistEntity(Object entity) {
    em.persist(entity);
    return entity;
  }

  public Object refreshEntity(Object entity) {
    em.refresh(entity);
    return entity;
  }

  public void removeEntity(Object entity) {
    em.remove(em.merge(entity));
  }

  public void createNewOrder(CustomerOrder newOrder) {
    persistEntity(newOrder);
  }

  public String processOrder(List cartItems) {

    return null;
  }

  private boolean performCreditCheck(Individual customer) {

    String ccnum = customer.getCcNum().toString();
    CreditCheckEndpointBean creditService = 
      service.getCreditCheckEndpointBeanPort();
    return creditService.creditCheck(ccnum);

  }

  public String processOrder(Individual customer) {
    String processStatus = null;
    if (!em.contains(customer)) {
      customer = em.find(Individual.class, customer.getId());
    }

    if (performCreditCheck(customer)) {
      CustomerOrder order = new CustomerOrder();
      order.setCreationDate(new Timestamp(System.currentTimeMillis()));

      try {

        Collection<CartItem> cartCol = customer.getCartItemCollection();
        Iterator cartIter = cartCol.iterator();
        final List<CartItem> cartItems = new ArrayList();
        while (cartIter.hasNext()) {
          CartItem cItem = (CartItem)cartIter.next();
          OrderItem oItem = new OrderItem();
          Long qty = cItem.getQuantity();
          oItem.setQuantity(qty);
          oItem.setOrderDate(new Timestamp(System.currentTimeMillis()));
          oItem.setWine(cItem.getWine());
          Wine tempWine = cItem.getWine();
          Double d = tempWine.getRetailPrice();
          Double price = d * cItem.getQuantity();
          oItem.setPrice(price);
          order.addOrderItem(oItem);
          cartItems.add(cItem);
        }

        for (CartItem cartItem: cartItems) {
          customer.removeCartItem(cartItem);
          em.remove(em.merge(cartItem));
        }
        PurchaseOrder po = new PurchaseOrder();
        po.setCustomerId(customer.getId());
        po.setCustomerOrder(order);

        sendPOtoMDB(po);
      }
      catch (Exception e) {
        e.printStackTrace();
      }

      processStatus = 
          "Purchase Order sent for processing to the process queue";

    }
    else {
      processStatus = " Invalid Credit Card number or credit check failed";
    }

    return processStatus;
  }

  private void sendPOtoMDB(PurchaseOrder po) {
    //send PO to MDB now
    Connection connection = null;
    Session session = null;
    try {
      connection = poTopicCF.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(poTopic);
      ObjectMessage objMessage = session.createObjectMessage();
      objMessage.setObject(po);
      producer.send(objMessage);
      session.close();
      connection.close();
    }
    catch (JMSException e) {
      e.printStackTrace();

    }

  }
}

