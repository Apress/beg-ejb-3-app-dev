package com.apress.ejb3.wineapp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@NamedQuery(name = "findAllAddress", query = "select object(o) from Address o")
@Table(name = "ADDRESS")
public class

Address
  implements Serializable
{
  protected String city;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
                  generator = "SEQ_ID_GENERATOR")
  @Column(nullable = false)
  protected Long id;
  @Version
  protected Integer version;
  protected String state;
  protected String street1;
  protected String street2;
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

  public Long getZipCode() {
    return zipCode;
  }

  public void setZipCode(Long zipCode) {
    this.zipCode = zipCode;
  }
}
