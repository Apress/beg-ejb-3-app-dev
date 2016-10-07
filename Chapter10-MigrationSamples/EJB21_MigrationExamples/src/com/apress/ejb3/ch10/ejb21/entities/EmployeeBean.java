package com.apress.ejb3.ch10.ejb21.entities;

import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class EmployeeBean
  implements EntityBean
{
  private EntityContext _context;

  public Long ejbCreate() throws CreateException {
    return null;
  }

  public void ejbPostCreate() {
  }

  public Long ejbCreate(Long empno, Long deptno) throws CreateException {
    setEmpno(empno);
    setDeptno(deptno);
    return empno;
  }

  public void ejbPostCreate(Long empno, Long deptno) {
  }

  public void setEntityContext(EntityContext context)
    throws EJBException {
    _context = context;
  }

  public void unsetEntityContext()
    throws EJBException {
    _context = null;
  }

  public void ejbRemove()
    throws EJBException, RemoveException {
  }

  public void ejbActivate()
    throws EJBException {
  }

  public void ejbPassivate()
    throws EJBException {
  }

  public void ejbLoad()
    throws EJBException {
  }

  public void ejbStore()
    throws EJBException {
  }

  public abstract Long getEmpno();

  public abstract void setEmpno(Long empno);

  public abstract String getEname();

  public abstract void setEname(String ename);

  public abstract String getJob();

  public abstract void setJob(String job);

  public abstract Timestamp getHiredate();

  public abstract void setHiredate(Timestamp hiredate);

  public abstract Double getSal();

  public abstract void setSal(Double sal);

  public abstract Double getComm();

  public abstract void setComm(Double comm);

  public abstract Long getDeptno();

  public abstract void setDeptno(Long deptno);

  public abstract EmployeeLocal getManager();

  public abstract void setManager(EmployeeLocal manager);

  public abstract Collection<EmployeeLocal> getManagedEmployees();

  public abstract void setManagedEmployees(Collection<EmployeeLocal> managedEmployees);

  public abstract DepartmentLocal getDepartment();

  public abstract void setDepartment(DepartmentLocal department);
}
