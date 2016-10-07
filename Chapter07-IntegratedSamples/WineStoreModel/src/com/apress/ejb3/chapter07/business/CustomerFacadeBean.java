package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.Customer;
import com.apress.ejb3.wineapp.Individual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "CustomerFacade")
public class CustomerFacadeBean
  implements CustomerFacade, CustomerFacadeLocal
{
  @PersistenceContext(unitName = "wineStoreUnit")
  private EntityManager em;

  public CustomerFacadeBean() {
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

  public void AddCustomer(Customer customer) {
    persistEntity(customer);
  }

  public void AddCustomer(Individual customer) {
    persistEntity(customer);
  }

  /** <code>select object(cust) from Individual cust where cust.email = :email</code> */
  public Individual findCustomerByEmail(Object email) {
    return (Individual)em.createNamedQuery("findCustomerByEmail").setParameter("email", 
                                                                               email).getSingleResult();
  }
}
