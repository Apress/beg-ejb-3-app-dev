package com.apress.ejb3.wineapp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("foo")
@DiscriminatorColumn(name = "TYPE", 
                     discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "findAllBusinessContact", 
            query = "select object(o) from BusinessContact o")
@Table(name = "BUSINESS_CONTACT")
@SequenceGenerator(name = "SEQ_ID_GENERATOR", sequenceName = "EJB_SEQ_ID_GEN")
@TableGenerator(name = "TABLE_ID_GENERATOR", table = "EJB_TAB_ID_GEN", 
                pkColumnName = "ID_NAME", valueColumnName = "SEQ_VALUE", 
                pkColumnValue = "SEQ_GEN")
public abstract class BusinessContact
  implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, 
                  generator = "TABLE_ID_GENERATOR")
  @Column(nullable = false)
  protected Long id;
  @Column(name = "FIRST_NAME")
  protected String firstName;
  @Column(name = "LAST_NAME")
  protected String lastName;
  protected String phone;
  protected String type;
  @Version
  protected Integer version;

  public BusinessContact() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
