package com.apress.ejb3.ch10.ejb21.hello;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 * Hello.java:  EJB 2.1 Remote interface for the 'Hello' Session bean
 */
public interface Hello
  extends EJBObject
{
  String sayHello(String name)
    throws RemoteException;
}
