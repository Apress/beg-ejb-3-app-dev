package com.apress.ejb3.ch10.ejb21.entities;

import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.EJBLocalObject;

public interface EmployeeLocal
  extends EJBLocalObject
{
  Long getEmpno();

  String getEname();

  void setEname(String ename);

  String getJob();

  void setJob(String job);

  Timestamp getHiredate();

  void setHiredate(Timestamp hiredate);

  Double getSal();

  void setSal(Double sal);

  Double getComm();

  void setComm(Double comm);

  Long getDeptno();

  void setDeptno(Long deptno);

  EmployeeLocal getManager();

  void setManager(EmployeeLocal manager);

  Collection<EmployeeLocal> getManagedEmployees();

  void setManagedEmployees(Collection<EmployeeLocal> managedEmployees);

  DepartmentLocal getDepartment();

  void setDepartment(DepartmentLocal department);
}
