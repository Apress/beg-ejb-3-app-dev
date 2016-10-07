package com.apress.ejb3.chapter05;

import java.rmi.RemoteException;
import javax.ejb.Remote;

@Remote
public interface OrderProcessing
{
  String SendOrderStatus()
    throws RemoteException;
}
