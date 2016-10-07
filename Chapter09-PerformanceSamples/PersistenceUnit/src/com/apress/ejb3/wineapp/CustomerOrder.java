package com.apress.ejb3.wineapp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@NamedQuery(name = "findAllCustomerOrder", 
            query = "select object(o) from CustomerOrder o")
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder
  implements Serializable
{

  @Column(name = "CREATION_DATE")
  protected Timestamp creationDate;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
                  generator = "SEQ_ID_GENERATOR")
  @Column(nullable = false)
  protected Long id;

  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
  protected Customer customer;

  @OneToMany(mappedBy = "customerOrder", cascade = { CascadeType.ALL })
  protected Collection<OrderItem> orderItemCollection;

  @Version
  protected Integer version;

  public CustomerOrder() {
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Collection<OrderItem> getOrderItemCollection() {
    if (orderItemCollection == null) {
      orderItemCollection = new ArrayList();
    }
    return orderItemCollection;
  }

  public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
    this.orderItemCollection = orderItemCollection;
  }

  public OrderItem addOrderItem(OrderItem orderItem) {
    getOrderItemCollection().add(orderItem);
    orderItem.setCustomerOrder(this);
    return orderItem;
  }

  public OrderItem removeOrderItem(OrderItem orderItem) {
    getOrderItemCollection().remove(orderItem);
    orderItem.setCustomerOrder(null);
    return orderItem;
  }
}
