package com.apress.ejb3.ch04.mappedsuperclass;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * Employee:  A root entity in a JOINED inheritance hierarchy, and a subclass
 * of the mapped superclass Person
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@NamedQuery(name = "Employee.findAll", query = "select o from Employee o")
@Table(name = "CH04_MS_EMPLOYEE")
public class Employee
  extends Person
  implements Serializable
{
  protected String email;
  protected String dept;

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
}
