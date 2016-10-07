package com.apress.ejb3.ch10.ejb21.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWebService
  extends Remote
{
  String sayHello(String name)
    throws RemoteException;
}
