package com.apress.ejb3.ch04.embedded;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/*
 * FullTimeEmployee:  A concrete leaf entity
 */
@Entity
@NamedQuery(name = "FullTimeEmployee.findAll", 
            query = "select o from FullTimeEmployee o")
@DiscriminatorValue("FullTimeEmployee")
public class // Illustrating the default value
FullTimeEmployee
  extends Employee
  implements Serializable
{
  @Column(name = "ANNUAL_SALARY")
  protected Double annualSalary;

  @OneToMany(mappedBy = "manager", cascade = { CascadeType.ALL })
  public List<Employee> managedEmployees;

  public FullTimeEmployee() {
  }

  public Double getAnnualSalary() {
    return annualSalary;
  }

  public void setAnnualSalary(Double annualSalary) {
    this.annualSalary = annualSalary;
  }

  public List<Employee> getManagedEmployees() {
    if (managedEmployees == null) {
      managedEmployees = new ArrayList();
    }
    return managedEmployees;
  }

  public void setManagedEmployees(List<Employee> managedEmployees) {
    this.managedEmployees = managedEmployees;
  }

  public Employee addEmployee(Employee employee) {
    getManagedEmployees().add(employee);
    employee.setManager(this);
    return employee;
  }

  public Employee removeEmployee(Employee employee) {
    getManagedEmployees().remove(employee);
    employee.setManager(null);
    return employee;
  }
}
