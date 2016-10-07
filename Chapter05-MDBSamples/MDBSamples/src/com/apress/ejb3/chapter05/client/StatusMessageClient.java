package com.apress.ejb3.chapter05.client;

import com.apress.ejb3.chapter05.OrderProcessing;
import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class StatusMessageClient
{
  public StatusMessageClient() {
  }

  public static void main(String[] args) {
    StatusMessageClient messageClient = new StatusMessageClient();
    messageClient.doIt();
  }

  private void doIt() {
    System.out.println("Performing logic in doIt() ");

    InitialContext ic;

    try {
      ic = new InitialContext();
      System.out.println("OrderProcessing session bean lookup to be done");
      OrderProcessing orderProcessing = 
        (OrderProcessing)ic.lookup("com.apress.ejb3.chapter05.OrderProcessing");
      System.out.println("Invoking SendOrderStatus() business method now");
      System.out.println(orderProcessing.SendOrderStatus());
      System.out.println("Done !!!");

    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    catch (RemoteException re) {
      re.printStackTrace();
    }

  }
}
