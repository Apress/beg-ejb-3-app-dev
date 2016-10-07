package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.CartItem;

import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "ShoppingCart")
public class ShoppingCartBean
  implements ShoppingCart, ShoppingCartLocal, Serializable
{
  public ShoppingCartBean() {
  }

  @PersistenceContext(unitName = "wineStoreUnit")
  private EntityManager em;

  public Individual customer;

  @EJB
  CustomerFacadeLocal customerFacade;

  @EJB
  OrderProcessFacadeLocal opcFacade;

  public Individual getCustomer() {
    return customer;
  }

  private Individual getManagedCustomer() {
    if (!em.contains(customer)) {
      customer = em.find(Individual.class, customer.getId());
    }
    return customer;
  }

  public void addWineItem(Wine wine, Long quantity) {

    CartItem cartItem = new CartItem();
    cartItem.setQuantity(quantity);
    wine = em.find(Wine.class, wine.getId());
    cartItem.setWine(wine);
    cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));

    if (em.contains(customer)) {
      customer.addCartItem(cartItem);
    }
    else {
      customer = em.find(Individual.class, customer.getId());
      customer.addCartItem(cartItem);
    }

    customer = em.merge(customer);
  }

  public void addWineItem(Wine wine) {
    customer = em.find(Individual.class, customer.getId());
    CartItem cartItem = new CartItem();
    cartItem.setQuantity(new Long(20));
    wine = em.find(Wine.class, wine.getId());
    cartItem.setWine(wine);
    cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    customer.addCartItem(cartItem);
    customer = em.merge(customer);
  }

  public void removeWineItem(CartItem cartItem) {

    if (em.contains(customer)) {
      customer.removeCartItem(cartItem);
    }
    else {
      customer = em.find(Individual.class, customer.getId());
      customer.removeCartItem(cartItem);
    }
    customer = em.merge(customer);

  }

  public void setCustomer(Individual customer) {
    this.customer = customer;

  }

  public void addCartItemsTemporarily() {
    customer = em.merge(customer);
    List<Wine> wines = em.createNamedQuery("findAllWine").getResultList();
    for (Wine wine: wines) {
      final CartItem cartItem = new CartItem();
      cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
      cartItem.setQuantity(20L);
      cartItem.setWine(wine);
      customer.addCartItem(cartItem);
      em.merge(customer);

    }
  }

  public Individual findCustomer(Object email) {
    final Individual cust = customerFacade.findCustomerByEmail(email);
    setCustomer(cust);
    return cust;
  }

  public String SendOrderToOPC() {
    try {
      Individual customer = getCustomer();
      opcFacade.processOrder(customer);

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return "Your Order has been submitted - you will be notified about the status via email";
  }

  public List<CartItem> getAllCartItems(Individual customer) {

    if (em.contains(customer)) {
      return (List)customer.getCartItemCollection();
    }
    else {
      customer = em.find(Individual.class, customer.getId());
      return (List)customer.getCartItemCollection();
    }
  }
}
