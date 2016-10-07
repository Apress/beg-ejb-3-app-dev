package com.apress.ejb3.wineapp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorValue(value = "Individual")
@NamedQueries

( { @NamedQuery(name = "findAllIndividual", 
                query = "select object(o) from Individual o")
    , 
    @NamedQuery(name = "findCustomerByEmail", query = "select object(cust) from Individual cust where cust.email = :email")
    , 
    @NamedQuery(name = "fincCustomerById", query = "select object(cust) from Individual cust where cust.id = :id")
    } )
public class Individual
  extends Customer
  implements Serializable
{
  @Column(name = "CC_EXP_DATE")
  protected String ccExpDate;
  @Column(name = "CC_NUM")
  protected Long ccNum;

  public Individual() {
  }

  public String getCcExpDate() {
    return ccExpDate;
  }

  public void setCcExpDate(String ccExpDate) {
    this.ccExpDate = ccExpDate;
  }

  public Long getCcNum() {
    return ccNum;
  }

  public void setCcNum(Long ccNum) {
    this.ccNum = ccNum;
  }
}
