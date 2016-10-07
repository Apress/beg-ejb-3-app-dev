package com.apress.ejb3.ch08;

import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.Customer;
import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "OrderProcessorCMT")
public class OrderProcessorCMTBean
  implements OrderProcessorCMT
{
  @PersistenceContext(unitName = "wineStoreUnit")
  private EntityManager em;

  public OrderProcessorCMTBean() {
  }

  /**
   *  Remove any existing Customers with email 'xaction.head@yahoo.com' and any
   *  existing Wine with country 'United States'
   */
  public String initialize() {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append("Removed ");
    int i = 0;
    //  Remove any existing Customers with email 'xaction.head@yahoo.com'
    for (Individual customer: 
         findAllCustomersByEmail("xaction.head@yahoo.com")) {
      em.remove(customer);
      i++;
    }
    strBuf.append(i);
    strBuf.append(" Customer(s) and ");

    //  Remove any existing Wine with country 'United States'
    i = 0;
    for (Wine wine: findWineByCountry("United States")) {
      em.remove(wine);
      i++;
    }
    strBuf.append(i);
    strBuf.append(" Wine(s)");
    return strBuf.toString();
  }

  /**
   * Create a new CustomerOrder from the items in a Customer's cart.
   * Creates a new CustomerOrder entity, and then creates a new OrderItem
   * entity for each CartItem found in the Customer's cart.
   *
   * Using CMT w/ the default Required xaction attribute, if this method is
   * invoked without a transaction context, a new transaction will be created
   * by the EJB container upon invoking the method, and committed upon
   * successfully completing the method.
   *
   * @return a status message (plain text)
   */
  public CustomerOrder createCustomerOrder(Customer customer)
    throws Exception {
    return createCustomerOrderUsingSupports(customer);
  }

  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
  public CustomerOrder createCustomerOrderUsingSupports(Customer customer)
    throws Exception {
    if (customer == null) {
      throw new IllegalArgumentException("OrderProcessingBean.createCustomerOrder():  Customer not specified");
    }

    final Long custId = customer.getId();
    customer = em.merge(customer);

    if (customer == null) {
      throw new Exception("Customer with id " + custId + " not found!");
    }

    final CustomerOrder customerOrder = new CustomerOrder();
    customerOrder.setCustomer(customer);
    final Timestamp orderDate = new Timestamp(System.currentTimeMillis());
    final List<CartItem> cartItemList = 
      new ArrayList(customer.getCartItemCollection());
    for (CartItem cartItem: cartItemList) {
      //  Create a new OrderItem for this CartItem
      final OrderItem orderItem = new OrderItem();
      orderItem.setOrderDate(orderDate);
      orderItem.setPrice(cartItem.getWine().getRetailPrice());
      orderItem.setQuantity(cartItem.getQuantity());
      orderItem.setStatus("Order Created");
      orderItem.setWine(cartItem.getWine());
      customerOrder.addOrderItem(orderItem);

      //  Remove the CartItem
      customer.removeCartItem(cartItem);
      em.remove(cartItem);
    }

    return persistEntity(customerOrder);
  }

  public <T> T persistEntity(T entity) {
    em.persist(entity);
    return entity;
  }

  /** <code>select object(cust) from Individual cust where cust.email = :email</code> */
  public List<Individual> findAllCustomersByEmail(Object email) {
    return em.createNamedQuery("findCustomerByEmail").setParameter("email", 
                                                                   email).getResultList();
  }

  /** <code>select wine from Wine wine where wine.country = :country</code> */
  public List<Wine> findWineByCountry(Object country) {
    return em.createNamedQuery("findWineByCountry").setParameter("country", 
                                                                 country).getResultList();
  }
}
