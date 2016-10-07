package com.apress.ejb3.wineapp;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorValue(value = "OrderItem")
@NamedQuery(name = "findAllOrderItem", 
            query = "select object(o) from OrderItem o")
public class OrderItem
  extends WineItem
  implements Serializable
{
  @Column(name = "ORDER_DATE")
  protected Timestamp orderDate;
  protected Double price;
  @Column(name = "SHIP_DATE")
  protected String shipDate;
  protected String status;

  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ORDER_ID", referencedColumnName = "ID")
  protected CustomerOrder customerOrder;

  public OrderItem() {
  }

  public Timestamp getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Timestamp orderDate) {
    this.orderDate = orderDate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getShipDate() {
    return shipDate;
  }

  public void setShipDate(String shipDate) {
    this.shipDate = shipDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CustomerOrder getCustomerOrder() {
    return customerOrder;
  }

  public void setCustomerOrder(CustomerOrder customerOrder) {
    this.customerOrder = customerOrder;
  }
}
