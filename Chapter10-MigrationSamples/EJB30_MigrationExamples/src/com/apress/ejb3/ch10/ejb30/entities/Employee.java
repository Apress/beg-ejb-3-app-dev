package com.apress.ejb3.ch10.ejb30.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Employee.findAll", query = "select o from Employee o")
@Table(name = "EMP")
public class Employee
  implements Serializable
{
  protected Double comm;
  @Id
  @Column(nullable = false)
  protected Long empno;
  protected String ename;
  protected Timestamp hiredate;
  protected String job;
  protected Double sal;
  @ManyToOne
  @JoinColumn(name = "MGR", referencedColumnName = "EMPNO")
  protected Employee employee;
  @OneToMany(mappedBy = "employee")
  protected Collection<Employee> employeeCollection;
  @ManyToOne
  @JoinColumn(name = "DEPTNO", referencedColumnName = "DEPTNO")
  protected Department department;

  public Employee() {
  }

  public Double getComm() {
    return comm;
  }

  public void setComm(Double comm) {
    this.comm = comm;
  }

  public Long getEmpno() {
    return empno;
  }

  public void setEmpno(Long empno) {
    this.empno = empno;
  }

  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public Timestamp getHiredate() {
    return hiredate;
  }

  public void setHiredate(Timestamp hiredate) {
    this.hiredate = hiredate;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Double getSal() {
    return sal;
  }

  public void setSal(Double sal) {
    this.sal = sal;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Collection<Employee> getEmployeeCollection() {
    return employeeCollection;
  }

  public void setEmployeeCollection(Collection<Employee> employeeCollection) {
    this.employeeCollection = employeeCollection;
  }

  public Employee addEmployee(Employee employee) {
    getEmployeeCollection().add(employee);
    employee.setEmployee(this);
    return employee;
  }

  public Employee removeEmployee(Employee employee) {
    getEmployeeCollection().remove(employee);
    employee.setEmployee(null);
    return employee;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
}