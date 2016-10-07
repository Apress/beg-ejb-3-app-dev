package com.apress.ejb3.chapter02.client;

import com.apress.ejb3.chapter02.ShoppingCart;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ShoppingCartTest
{
  public ShoppingCartTest() {
  }

  @EJB
  static ShoppingCart shoppingCart;

  public static void main(String[] args) {
    ShoppingCartTest shoppingCartTest = new ShoppingCartTest();
    shoppingCartTest.doTest();
  }

  void doTest() {
    InitialContext ic;

    try {
      ic = new InitialContext();
      System.out.println("ShoppingCart Lookup");
      System.out.println("Adding Wine Item");
      shoppingCart.addWineItem("Zinfandel");
      System.out.println("Printing Cart Items");
      ArrayList cartItems = shoppingCart.getCartItems();
      for (String wine: (List<String>)cartItems) {
        System.out.println(wine);
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
