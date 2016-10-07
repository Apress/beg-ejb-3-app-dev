package com.apress.ejb3.wineapp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorValue(value = "Distributor")
@NamedQuery(name = "findAllDistributor", 
            query = "select object(o) from Distributor o")
@Table(name = "DISTRIBUTOR")
public class Distributor
  extends Customer
  implements Serializable
{
  @Column(name = "COMPANY_NAME")
  protected String companyName;
  protected Long discount;
  @Column(name = "MEMBER_STATUS")
  protected String memberStatus;

  public Distributor() {
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Long getDiscount() {
    return discount;
  }

  public void setDiscount(Long discount) {
    this.discount = discount;
  }

  public String getMemberStatus() {
    return memberStatus;
  }

  public void setMemberStatus(String memberStatus) {
    this.memberStatus = memberStatus;
  }
}
