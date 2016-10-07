package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.CartItem;

import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.Wine;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal
{

  void removeWineItem(CartItem cartItem);

  void setCustomer(Individual customer);

  void addCartItemsTemporarily();

  Individual findCustomer(Object email);

  void addWineItem(Wine wine, Long quantity);

  String SendOrderToOPC();

  List<CartItem> getAllCartItems(Individual customer);
}
