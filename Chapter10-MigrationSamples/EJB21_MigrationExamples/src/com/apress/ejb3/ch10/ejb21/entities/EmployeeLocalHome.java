package com.apress.ejb3.ch10.ejb21.entities;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface EmployeeLocalHome
  extends EJBLocalHome
{
  EmployeeLocal create()
    throws CreateException;

  EmployeeLocal findByPrimaryKey(Long primaryKey)
    throws FinderException;

  Collection findAll()
    throws FinderException;

  EmployeeLocal create(Long empno, Long deptno)
    throws CreateException;
}
