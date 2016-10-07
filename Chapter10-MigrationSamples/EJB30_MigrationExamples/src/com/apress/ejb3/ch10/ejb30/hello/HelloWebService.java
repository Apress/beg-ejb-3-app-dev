package com.apress.ejb3.ch10.ejb30.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.jws.WebService;

@WebService
public interface HelloWebService
  extends Remote
{
  String sayHello(String name)
    throws RemoteException;
}
