package com.apress.ejb3.wineapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorValue(value = "Supplier")
@NamedQuery(name = "findAllSupplier", 
            query = "select object(o) from Supplier o")
public class Supplier
  extends BusinessContact
  implements Serializable
{
  @ManyToOne
  @JoinColumn(name = "PAYMENT_ADDRESS", referencedColumnName = "ID")
  protected Address address;

  @ManyToMany(mappedBy = "suppliers")
  protected Collection<Wine> wines;

  public Supplier() {
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Collection<Wine> getWines() {
    if (wines == null) {
      wines = new ArrayList();
    }
    return wines;
  }
}
