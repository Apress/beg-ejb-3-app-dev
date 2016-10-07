package com.apress.ejb3.ch04.joined;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * Employee:  An abstract entity
 */
@Entity
@NamedQuery(name = "Employee.findAll", query = "select o from Employee o")
@Table(name = "CH04_JOIN_EMPLOYEE")
public abstract class Employee
  extends Person
  implements Serializable
{
  protected String email;
  protected String dept;
  @ManyToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "MANAGER", referencedColumnName = "ID")
  protected FullTimeEmployee manager;

  public Employee() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDept() {
    return dept;
  }

  public void setDept(String dept) {
    this.dept = dept;
  }

  public FullTimeEmployee getManager() {
    return manager;
  }

  public void setManager(FullTimeEmployee manager) {
    this.manager = manager;
  }
}
