package com.apress.ejb3.ch04.embedded;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * Address:  An embeddable non-entity class
 */
@Embeddable
public class Address
  implements Serializable
{
  protected String city;
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
