package com.apress.ejb3.ch03;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "CustomerOrder.findAll", 
            query = "select o from CustomerOrder o")
@Table(name = "CH03_CUSTOMER_ORDER")
public class CustomerOrder
  implements Serializable
{
  private String city;
  @Column(name = "CREATION_DATE")
  private Timestamp creationDate;
  @Id
  @Column(nullable = false)
  @GeneratedValue(generator = "SequenceIdGenerator")
  private Long id;
  private String state;
  private String status;
  private String street1;
  private String street2;
  private Long version;
  @Column(name = "ZIP_CODE")
  private String zipCode;
  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
  private Customer customer;

  public CustomerOrder() {
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
