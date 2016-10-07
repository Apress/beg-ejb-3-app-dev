package com.apress.ejb3.ch03;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface CustOrderMgr
{
  Object mergeEntity(Object entity);

  Object persistEntity(Object entity);

  List<Customer> queryCustomerFindAll();

  void removeCustomer(Customer customer);

  List<CustomerOrder> queryCustomerOrderFindAll();

  void removeCustomerOrder(CustomerOrder customerOrder);

  List<Address> queryAddressFindAll();

  void removeAddress(Address address);

  List<Customer> queryCustomerFindByEmail(Object email);
}
