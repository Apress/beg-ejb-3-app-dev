package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.CustomerOrder;

import com.apress.ejb3.wineapp.Individual;

import java.util.List;

import javax.ejb.Local;

@Local
public interface OrderProcessFacadeLocal
{
  Object mergeEntity(Object entity);

  Object persistEntity(Object entity);

  Object refreshEntity(Object entity);

  void removeEntity(Object entity);

  void createNewOrder(CustomerOrder newOrder);

  String processOrder(List cartItems);

  String processOrder(Individual customer);
}
