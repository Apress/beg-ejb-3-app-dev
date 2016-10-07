package com.apress.ejb3.ch04.tableperclass;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * PartTimeEmployee:  A concrete leaf entity
 */
@Entity
@NamedQuery(name = "PartTimeEmployee.findAll", 
            query = "select o from PartTimeEmployee o")
@Table(name = "CH04_TPC_PT_EMPLOYEE")
public class PartTimeEmployee
  extends Employee
  implements Serializable
{
  @Column(name = "HOURLY_WAGE")
  protected Double hourlyWage;

  public PartTimeEmployee() {
  }

  public Double getHourlyWage() {
    return hourlyWage;
  }

  public void setHourlyWage(Double hourlyWage) {
    this.hourlyWage = hourlyWage;
  }
}
