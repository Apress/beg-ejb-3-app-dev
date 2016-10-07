package com.apress.ejb3.ch10.ejb30.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Department.findAll", query = "select o from Department o")
@Table(name = "DEPT")
public class Department
  implements Serializable
{
  @Id
  @Column(nullable = false)
  protected Long deptno;
  protected String dname;
  protected String loc;
  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  protected Collection<Employee> employeeCollection;

  public Department() {
  }

  public Long getDeptno() {
    return deptno;
  }

  public void setDeptno(Long deptno) {
    this.deptno = deptno;
  }

  public String getDname() {
    return dname;
  }

  public void setDname(String dname) {
    this.dname = dname;
  }

  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }

  public Collection<Employee> getEmployeeCollection() {
    return employeeCollection;
  }

  public void setEmployeeCollection(Collection<Employee> employeeCollection) {
    this.employeeCollection = employeeCollection;
  }

  public Employee addEmployee(Employee employee) {
    getEmployeeCollection().add(employee);
    employee.setDepartment(this);
    return employee;
  }

  public Employee removeEmployee(Employee employee) {
    getEmployeeCollection().remove(employee);
    employee.setDepartment(null);
    return employee;
  }
}