package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.CustomerOrder;

import java.io.Serializable;

public class PurchaseOrder
  implements Serializable
{
  public PurchaseOrder() {
  }

  private Long customerId;
  private CustomerOrder customerOrder;

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerOrder(CustomerOrder customerOrder) {
    this.customerOrder = customerOrder;
  }

  public CustomerOrder getCustomerOrder() {
    return customerOrder;
  }
}
