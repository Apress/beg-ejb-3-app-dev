package com.apress.ejb3.ch10.ejb21.entities;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class DepartmentBean
  implements EntityBean
{
  private EntityContext _context;

  public Long ejbCreate() throws CreateException {
    return null;
  }

  public void ejbPostCreate() {
  }

  public Long ejbCreate(Long deptno) throws CreateException {
    setDeptno(deptno);
    return deptno;
  }

  public void ejbPostCreate(Long deptno) {
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

  public abstract Long getDeptno();

  public abstract void setDeptno(Long deptno);

  public abstract String getDname();

  public abstract void setDname(String dname);

  public abstract String getLoc();

  public abstract void setLoc(String loc);

  public abstract Collection<EmployeeLocal> getEmployees();

  public abstract void setEmployees(Collection<EmployeeLocal> employees);
}
