package com.apress.ejb3.chapter02;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ShoppingCartLocal
{
  void addWineItem(String wine);

  void removeWineItem(String item);

  ArrayList getCartItems();
}
