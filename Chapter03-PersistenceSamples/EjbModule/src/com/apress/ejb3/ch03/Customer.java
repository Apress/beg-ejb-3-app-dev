package com.apress.ejb3.ch03;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Customer.findAll", 
              query = "select o from Customer o"), 
  @NamedQuery(name = "Customer.findByEmail", 
              query = "select o from Customer o where o.email = :email")
})
@Table(name = "CH03_CUSTOMER")
public class Customer
  implements Serializable
{
  private String email;
  @Id
  @Column(nullable = false)
  @GeneratedValue(generator = "SequenceIdGenerator")
  private Long id;
  @OneToMany(mappedBy = "customer")
  private List<CustomerOrder> customerOrderList;
  @ManyToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "SHIPPING_ADDRESS", referencedColumnName = "ID")
  private Address shippingAddress;
  @ManyToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "BILLING_ADDRESS", referencedColumnName = "ID")
  private Address billingAddress;

  public Customer() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<CustomerOrder> getCustomerOrderList() {
    return customerOrderList;
  }

  public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
    this.customerOrderList = customerOrderList;
  }

  public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderList().add(customerOrder);
    customerOrder.setCustomer(this);
    return customerOrder;
  }

  public CustomerOrder removeCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderList().remove(customerOrder);
    customerOrder.setCustomer(null);
    return customerOrder;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address address) {
    this.shippingAddress = address;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address address1) {
    this.billingAddress = address1;
  }
}
