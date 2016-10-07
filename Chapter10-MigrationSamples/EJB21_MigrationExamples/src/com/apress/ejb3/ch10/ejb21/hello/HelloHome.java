package com.apress.ejb3.ch10.ejb21.hello;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloHome
  extends EJBHome
{
  Hello create()
    throws RemoteException, CreateException;
}
