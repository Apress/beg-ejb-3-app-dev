package com.apress.ejb3.ch10.ejb21.hr;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HRManagerHome
  extends EJBHome
{
  HRManager create()
    throws RemoteException, CreateException;
}
