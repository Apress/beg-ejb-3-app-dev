package com.apress.ejb3.ch03.client;

import com.apress.ejb3.ch03.Address;
import com.apress.ejb3.ch03.CustOrderMgr;
import com.apress.ejb3.ch03.Customer;
import javax.naming.Context;
import javax.naming.InitialContext;

public class CustomerOrderClient
{
  public static void main(String[] args) {
    try {
      final Context context = new InitialContext();
      CustOrderMgr custOrderMgr = 
        (CustOrderMgr)context.lookup("CustomerOrderManager");
      final Address address = new Address();
      address.setCity("San Mateo");
      address.setState("CA");
      address.setZipCode(94402L);
      final Customer customer = new Customer();
      customer.setEmail("wayzout@gmail.com");
      customer.setShippingAddress(address);
      custOrderMgr.persistEntity(customer);

      for (Customer cust: 
           custOrderMgr.queryCustomerFindByEmail("wayzout@gmail.com")) {
        System.out.println("Customer ID is " + cust.getId());
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
