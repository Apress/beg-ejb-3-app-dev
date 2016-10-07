package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.CustomerOrder;

import com.apress.ejb3.wineapp.Individual;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface OrderProcessFacade
{
  Object mergeEntity(Object entity);

  Object persistEntity(Object entity);

  Object refreshEntity(Object entity);

  void removeEntity(Object entity);

  void createNewOrder(CustomerOrder newOrder)
    throws RemoteException;

  String processOrder(List cartItems)
    throws RemoteException;

  String processOrder(Individual customer)
    throws RemoteException;
}
