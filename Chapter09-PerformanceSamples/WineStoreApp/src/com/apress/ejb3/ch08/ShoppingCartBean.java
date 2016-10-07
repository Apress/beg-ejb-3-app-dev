package com.apress.ejb3.ch08;

import com.apress.ejb3.wineapp.Address;
import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.InventoryItem;
import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "ShoppingCart")
public class ShoppingCartBean
  implements ShoppingCart, ShoppingCartLocal, Serializable
{

  @PersistenceContext(unitName = "wineStoreUnit")
  private
  EntityManager em;

  private Individual customer;

  public ShoppingCartBean() {
  }

  public void addWineItem(Wine wine, Long quantity) {

    CartItem cartItem = new CartItem();
    cartItem.setQuantity(quantity);
    wine = em.find(Wine.class, wine.getId());
    cartItem.setWine(wine);
    cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));

    if (em.contains(getCustomer())) {
      customer.addCartItem(cartItem);
    }
    else {
      customer = em.find(Individual.class, customer.getId());
      customer.addCartItem(cartItem);
    }

    customer = em.merge(customer);

  }

  public void addWine(CartItem cartItem) {
    getCustomer().addCartItem(cartItem);
    em.persist(cartItem);
    customer = em.merge(customer);
  }

  public void removeItem(CartItem cartItem) {
    getCustomer().removeCartItem(cartItem);
    em.merge(customer);
  }

  public String ProcessOrder() {
    Individual customer = getCustomer();
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
        deductInventory(tempWine, qty);
        cartItems.add(cItem);

      }
      for (CartItem cartItem: cartItems) {
        customer.removeCartItem(cartItem);
        em.remove(em.merge(cartItem));
      }
      em.persist(order);
      customer.addCustomerOrder(order);
      customer = em.merge(customer);
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return "success";
  }

  private void deductInventory(Wine tempWine, Long deductQty) {
    InventoryItem iItem = 
      (InventoryItem)em.createNamedQuery("findInventoryItemByWine").setParameter("wine", 
                                                                                 tempWine).getSingleResult();
    Long newQty = iItem.getQuantity() - deductQty;
    iItem.setQuantity(newQty);
    em.merge(iItem);
  }

  private Individual getCustomer() {
    if (customer == null) {
      /*
       * Create a customer that will be used for each stateful bean session
      */
      customer = new Individual();
      Address address = new Address();
      address.setStreet1("200" + "opy" + " Avenue " + "opy");
      address.setCity("San Mateo");
      address.setState("CA");
      address.setZipCode(new Long(94065));

      customer.setBillingAddress(address);
      customer.setCcExpDate("08/2010");
      customer.setCcNum(new Long(123456789));

      customer.setEmail("userNN@wineapp.com");
      customer.setFirstName("firstname");
      customer.setLastName("lastname");
      customer.setPhone("18008008000");
      customer.setShippingAddress(address);
      customer.setType("Individual");

      em.persist(address);
      em.persist(customer);

    }
    return customer;
  }

  /** <code>select object(o) from Wine o</code> */
  public List<Wine> findAllWine() {
    return em.createNamedQuery("findAllWine").getResultList();
  }

  public void setCustomer(Individual customer) {
    this.customer = customer;
  }

  public InventoryItem findInventoryItemByWine(Wine wine) {
    return (InventoryItem)em.createNamedQuery("findInventoryItemByWine").setParameter("wine", 
                                                                                      wine).getSingleResult();
  }
}
