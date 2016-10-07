package com.apress.ejb3.ch10.ejb30.hr;

import com.apress.ejb3.ch10.ejb30.entities.Department;
import com.apress.ejb3.ch10.ejb30.entities.Employee;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface HRManager
{
  Object mergeEntity(Object entity);

  Object persistEntity(Object entity);

  List<Department> queryDepartmentFindAll();

  void removeDepartment(Department department);

  List<Employee> queryEmployeeFindAll();

  void removeEmployee(Employee employee);
}
