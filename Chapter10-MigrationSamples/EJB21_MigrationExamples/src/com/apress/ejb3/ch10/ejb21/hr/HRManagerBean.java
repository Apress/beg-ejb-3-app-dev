package com.apress.ejb3.ch10.ejb21.hr;

import com.apress.ejb3.ch10.ejb21.entities.DepartmentLocal;
import com.apress.ejb3.ch10.ejb21.entities.DepartmentLocalDTO;
import com.apress.ejb3.ch10.ejb21.entities.DepartmentLocalHome;
import com.apress.ejb3.ch10.ejb21.entities.EmployeeLocal;
import com.apress.ejb3.ch10.ejb21.entities.EmployeeLocalDTO;
import com.apress.ejb3.ch10.ejb21.entities.EmployeeLocalHome;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HRManagerBean
  implements SessionBean
{
  private SessionContext _context;

  public void ejbCreate() {
  }

  public void setSessionContext(SessionContext context)
    throws EJBException {
    _context = context;
  }

  public void ejbRemove()
    throws EJBException {
  }

  public void ejbActivate()
    throws EJBException {
  }

  public void ejbPassivate()
    throws EJBException {
  }

  public void newDepartment(DepartmentLocalDTO departmentLocalDTO)
    throws CreateException, FinderException, NamingException {
    final DepartmentLocal departmentLocal = 
      getDepartmentLocalHome().create(departmentLocalDTO.getDeptno());
    departmentLocalDTO.copyToEntity(departmentLocal);
  }

  public Collection<DepartmentLocalDTO> retrieveAllDepartment()
    throws FinderException, NamingException {
    final Collection departmentLocalCollection = new ArrayList();
    final Iterator iter = getDepartmentLocalHome().findAll().iterator();
    while (iter.hasNext()) {
      departmentLocalCollection.add(new DepartmentLocalDTO((DepartmentLocal)iter.next()));
    }
    return departmentLocalCollection;
  }

  public void updateDepartment(DepartmentLocalDTO departmentLocalDTO)
    throws FinderException, NamingException {
    final DepartmentLocal departmentLocal = 
      retrieveDepartmentLocalByDTO(departmentLocalDTO);
    departmentLocalDTO.copyToEntity(departmentLocal);
  }

  public DepartmentLocalDTO refreshDepartment(DepartmentLocalDTO departmentLocalDTO)
    throws FinderException, NamingException {
    final DepartmentLocal departmentLocal = 
      retrieveDepartmentLocalByDTO(departmentLocalDTO);
    departmentLocalDTO.copyFromEntity(departmentLocal);
    return departmentLocalDTO;
  }

  private DepartmentLocalHome getDepartmentLocalHome()
    throws NamingException {
    final InitialContext context = new InitialContext();
    return (DepartmentLocalHome)context.lookup("java:comp/env/ejb/local/Department");
  }

  private DepartmentLocal retrieveDepartmentLocalByDTO(DepartmentLocalDTO departmentLocalDTO)
    throws FinderException, NamingException {
    return getDepartmentLocalHome().findByPrimaryKey(departmentLocalDTO.getDeptno());
  }

  public void newEmployee(EmployeeLocalDTO employeeLocalDTO)
    throws CreateException, FinderException, NamingException {
    final EmployeeLocal employeeLocal = 
      getEmployeeLocalHome().create(employeeLocalDTO.getEmpno(), 
                                    employeeLocalDTO.getDeptno());
    employeeLocalDTO.copyToEntity(employeeLocal);
    if (employeeLocalDTO.getDepartmentDTO() != null) {
      final DepartmentLocal department = 
        retrieveDepartmentLocalByDTO(employeeLocalDTO.getDepartmentDTO());
      employeeLocal.setDepartment(department);
    }
    if (employeeLocalDTO.getManagerDTO() != null) {
      final EmployeeLocal manager = 
        retrieveEmployeeLocalByDTO(employeeLocalDTO.getManagerDTO());
      employeeLocal.setManager(manager);
    }
  }

  public Collection<EmployeeLocalDTO> retrieveAllEmployee()
    throws FinderException, NamingException {
    final Collection employeeLocalCollection = new ArrayList();
    final Iterator iter = getEmployeeLocalHome().findAll().iterator();
    while (iter.hasNext()) {
      employeeLocalCollection.add(new EmployeeLocalDTO((EmployeeLocal)iter.next()));
    }
    return employeeLocalCollection;
  }

  public void updateEmployee(EmployeeLocalDTO employeeLocalDTO)
    throws FinderException, NamingException {
    final EmployeeLocal employeeLocal = 
      retrieveEmployeeLocalByDTO(employeeLocalDTO);
    employeeLocalDTO.copyToEntity(employeeLocal);
  }

  public EmployeeLocalDTO refreshEmployee(EmployeeLocalDTO employeeLocalDTO)
    throws FinderException, NamingException {
    final EmployeeLocal employeeLocal = 
      retrieveEmployeeLocalByDTO(employeeLocalDTO);
    employeeLocalDTO.copyFromEntity(employeeLocal);
    return employeeLocalDTO;
  }

  private EmployeeLocalHome getEmployeeLocalHome()
    throws NamingException {
    final InitialContext context = new InitialContext();
    return (EmployeeLocalHome)context.lookup("java:comp/env/ejb/local/Employee");
  }

  private EmployeeLocal retrieveEmployeeLocalByDTO(EmployeeLocalDTO employeeLocalDTO)
    throws FinderException, NamingException {
    return getEmployeeLocalHome().findByPrimaryKey(employeeLocalDTO.getEmpno());
  }

  public void removeEntity(Object entityDTO)
    throws RemoteException, FinderException, RemoveException, NamingException {
    final Object obj = getEntityByDTO(entityDTO);
    if (obj instanceof EJBLocalObject) {
      ((EJBLocalObject)obj).remove();
    }
    else if (obj instanceof EJBObject) {
      ((EJBObject)obj).remove();
    }
  }

  private Object getEntityByDTO(Object entityDTO)
    throws FinderException, NamingException {
    if (entityDTO instanceof DepartmentLocalDTO) {
      return retrieveDepartmentLocalByDTO((DepartmentLocalDTO)entityDTO);
    }
    if (entityDTO instanceof EmployeeLocalDTO) {
      return retrieveEmployeeLocalByDTO((EmployeeLocalDTO)entityDTO);
    }
    return null;
  }
}
