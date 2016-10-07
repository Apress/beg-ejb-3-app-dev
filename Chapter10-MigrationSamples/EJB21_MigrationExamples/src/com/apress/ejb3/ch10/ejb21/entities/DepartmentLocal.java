package com.apress.ejb3.ch10.ejb21.entities;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

public interface DepartmentLocal
  extends EJBLocalObject
{
  Long getDeptno();

  String getDname();

  void setDname(String dname);

  String getLoc();

  void setLoc(String loc);

  Collection<EmployeeLocal> getEmployees();

  void setEmployees(Collection<EmployeeLocal> employees);
}
