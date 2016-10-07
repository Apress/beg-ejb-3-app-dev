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
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.UserTransaction;

@Stateful(name = "OrderProcessorBMT")
@TransactionManagement(TransactionManagementType.BEAN)
public class OrderProcessorBMTBean
  implements OrderProcessorBMT
{
  @PersistenceContext(unitName = "wineStoreUnit", 
                      type = PersistenceContextType.EXTENDED)
  private EntityManager em;

  @Resource
  private UserTransaction ut;

  public OrderProcessorBMTBean() {
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
    if (customer == null) {
      throw new IllegalArgumentException("OrderProcessingBean.createCustomerOrder():  Customer not specified");
    }

    customer = em.find(Customer.class, customer.getId());

    CustomerOrder customerOrder = new CustomerOrder();
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

    em.persist(customerOrder);

    return customerOrder;
  }

  public void beginTrans()
    throws Exception {
    ut.begin();
  }

  public void commitTrans()
    throws Exception {
    ut.commit();
  }

  public void rollbackTrans()
    throws Exception {
    ut.rollback();
  }

  public <T> T mergeEntity(T entity) {
    return em.merge(entity);
  }

  public <T> T persistEntity(T entity) {
    em.persist(entity);
    return entity;
  }

  public <T> T refreshEntity(T entity) {
    em.refresh(entity);
    return entity;
  }

  public void removeEntity(Object entity) {
    em.remove(em.merge(entity));
  }

  /** <code>select o from Customer o</code> */
  public List<Customer> findAllCustomer() {
    return em.createNamedQuery("findAllCustomer").getResultList();
  }

  /** <code>select cust from Customer cust where cust.email = :email</code> */
  public List<Customer> findCustomerByEmailInCustomer(Object email) {
    return em.createNamedQuery("findCustomerByEmailInCustomer").setParameter("email", 
                                                                             email).getResultList();
  }

  /** <code>select object(o) from Individual o</code> */
  public List<Individual> findAllIndividual() {
    return em.createNamedQuery("findAllIndividual").getResultList();
  }

  /** <code>select object(cust) from Individual cust where cust.email = :email</code> */
  public List<Individual> findAllCustomersByEmail(Object email) {
    return em.createNamedQuery("findCustomerByEmail").setParameter("email", 
                                                                   email).getResultList();
  }

  /** <code>select cust from Individual cust where cust.lastName = :lastName</code> */
  public List<Individual> findCustomerByLastName(Object lastName) {
    return em.createNamedQuery("findCustomerByLastName").setParameter("lastName", 
                                                                      lastName).getResultList();
  }

  /** <code>select o from Wine o</code> */
  public List<Wine> findAllWine() {
    return em.createNamedQuery("findAllWine").getResultList();
  }

  /** <code>select wine from Wine wine where wine.year = :year</code> */
  public List<Wine> findWineByYear(Object year) {
    return em.createNamedQuery("findWineByYear").setParameter("year", 
                                                              year).getResultList();
  }

  /** <code>select wine from Wine wine where wine.country = :country</code> */
  public List<Wine> findWineByCountry(Object country) {
    return em.createNamedQuery("findWineByCountry").setParameter("country", 
                                                                 country).getResultList();
  }

  /** <code>select wine from Wine wine where wine.varietal = :varietal</code> */
  public List<Wine> findWineByVarietal(Object varietal) {
    return em.createNamedQuery("findWineByVarietal").setParameter("varietal", 
                                                                  varietal).getResultList();
  }
}
