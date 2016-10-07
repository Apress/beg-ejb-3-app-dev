package com.apress.ejb3.chapter02;

import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ShoppingCart
{
  void addWineItem(String wine);

  void removeWineItem(String item);

  ArrayList getCartItems();
}
