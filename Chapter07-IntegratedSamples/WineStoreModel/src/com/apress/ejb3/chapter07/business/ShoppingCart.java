package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.CartItem;

import com.apress.ejb3.wineapp.Customer;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.Wine;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ShoppingCart
{

  void removeWineItem(CartItem cartItem)
    throws RemoteException;

  void setCustomer(Individual customer);

  void addCartItemsTemporarily();

  void addWineItem(Wine wine, Long quantity)
    throws RemoteException;

  String SendOrderToOPC();

  List<CartItem> getAllCartItems(Individual customer);

  Individual findCustomer(Object email);
}
