package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.Customer;
import com.apress.ejb3.wineapp.Individual;
import javax.ejb.Remote;

@Remote
public interface CustomerFacade
{
  Object mergeEntity(Object entity);

  Object persistEntity(Object entity);

  Object refreshEntity(Object entity);

  void removeEntity(Object entity);

  void AddCustomer(Customer customer);

  void AddCustomer(Individual customer);

  Individual findCustomerByEmail(Object email);
}
