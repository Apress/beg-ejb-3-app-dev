package com.apress.ejb3.ch10.ejb21.hello;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * HelloBean.java:  EJB 2.1 Bean class for the 'Hello' Session bean
 */
public class HelloBean
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

  public String sayHello(String name) {
    return "Hello " + name;
  }
}
