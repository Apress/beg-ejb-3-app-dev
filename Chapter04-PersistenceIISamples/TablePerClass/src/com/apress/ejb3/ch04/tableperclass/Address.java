package com.apress.ejb3.ch04.tableperclass;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/*
 * Address:  A standalone entity
 */
@Entity
@NamedQuery(name = "Address.findAll", query = "select o from Address o")
@SequenceGenerator(name = "AddressIdGenerator", 
                   sequenceName = "CH04_TPC_ADDRESS_SEQ", initialValue = 100, 
                   allocationSize = 20)
@Table(name = "CH04_TPC_ADDRESS")
public class Address
  implements Serializable
{
  protected String city;
  @Id
  @Column(nullable = false)
  @GeneratedValue(generator = "AddressIdGenerator")
  protected Long id;
  protected String state;
  protected String street1;
  protected String street2;
  @Version
  protected Long version;
  @Column(name = "ZIP_CODE")
  protected Long zipCode;

  public Address() {
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
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

  public Long getZipCode() {
    return zipCode;
  }

  public void setZipCode(Long zipCode) {
    this.zipCode = zipCode;
  }
}
