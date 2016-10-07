package com.apress.ejb3.ch10.ejb21.entities;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface DepartmentLocalHome
  extends EJBLocalHome
{
  DepartmentLocal create()
    throws CreateException;

  DepartmentLocal findByPrimaryKey(Long primaryKey)
    throws FinderException;

  DepartmentLocal create(Long deptno)
    throws CreateException;

  Collection findAll()
    throws FinderException;
}
