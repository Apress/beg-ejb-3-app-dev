package com.apress.ejb3.ch03;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Address.findAll", query = "select o from Address o")
@SequenceGenerator(name = "SequenceIdGenerator", 
                   sequenceName = "CH03_EJB_SEQ_ID_GEN", initialValue = 100, 
                   allocationSize = 20)
@Table(name = "CH03_ADDRESS")
public class Address
  implements Serializable
{
  private String city;
  @Id
  @Column(nullable = false)
  @GeneratedValue(generator = "SequenceIdGenerator")
  private Long id;
  private String state;
  private String street1;
  private String street2;
  private Long version;
  @Column(name = "ZIP_CODE")
  private Long zipCode;
  @OneToMany(mappedBy = "shippingAddress")
  private List<Customer> shippingCustomers;
  @OneToMany(mappedBy = "billingAddress")
  private List<Customer> billingCustomers;

  public Address() {
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getZipCode() {
    return zipCode;
  }

  public void setZipCode(Long zipCode) {
    this.zipCode = zipCode;
  }

  public List<Customer> getShippingCustomers() {
    return shippingCustomers;
  }

  public void setShippingCustomers(List<Customer> customerList) {
    this.shippingCustomers = customerList;
  }

  public Customer addCustomer(Customer customer) {
    getShippingCustomers().add(customer);
    customer.setShippingAddress(this);
    return customer;
  }

  public Customer removeCustomer(Customer customer) {
    getShippingCustomers().remove(customer);
    customer.setShippingAddress(null);
    return customer;
  }

  public List<Customer> getBillingCustomers() {
    return billingCustomers;
  }

  public void setBillingCustomers(List<Customer> customerList1) {
    this.billingCustomers = customerList1;
  }

  public Customer addCustomer1(Customer customer) {
    getBillingCustomers().add(customer);
    customer.setBillingAddress(this);
    return customer;
  }

  public Customer removeCustomer1(Customer customer) {
    getBillingCustomers().remove(customer);
    customer.setBillingAddress(null);
    return customer;
  }
}
