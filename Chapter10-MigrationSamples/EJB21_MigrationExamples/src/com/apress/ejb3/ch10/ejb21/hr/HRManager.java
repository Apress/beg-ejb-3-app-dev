package com.apress.ejb3.ch10.ejb21.hr;

import com.apress.ejb3.ch10.ejb21.entities.DepartmentLocalDTO;
import com.apress.ejb3.ch10.ejb21.entities.EmployeeLocalDTO;
import java.rmi.RemoteException;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

public interface HRManager
  extends EJBObject
{
  void newDepartment(DepartmentLocalDTO departmentLocalDTO)
    throws RemoteException, CreateException, FinderException, NamingException;

  Collection<DepartmentLocalDTO> retrieveAllDepartment()
    throws RemoteException, FinderException, NamingException;

  void updateDepartment(DepartmentLocalDTO departmentLocalDTO)
    throws RemoteException, FinderException, NamingException;

  DepartmentLocalDTO refreshDepartment(DepartmentLocalDTO departmentLocalDTO)
    throws RemoteException, FinderException, NamingException;

  void newEmployee(EmployeeLocalDTO employeeLocalDTO)
    throws RemoteException, CreateException, FinderException, NamingException;

  Collection<EmployeeLocalDTO> retrieveAllEmployee()
    throws RemoteException, FinderException, NamingException;

  void updateEmployee(EmployeeLocalDTO employeeLocalDTO)
    throws RemoteException, FinderException, NamingException;

  EmployeeLocalDTO refreshEmployee(EmployeeLocalDTO employeeLocalDTO)
    throws RemoteException, FinderException, NamingException;

  void removeEntity(Object entityDTO)
    throws RemoteException, FinderException, RemoveException, NamingException;
}
