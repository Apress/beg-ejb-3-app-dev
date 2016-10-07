package com.apress.ejb3.chapter07.client;

import com.apress.ejb3.chapter07.business.SearchFacade;
import com.apress.ejb3.chapter07.business.ShoppingCart;
import com.apress.ejb3.wineapp.Wine;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;

public class ShoppingCartTest
{
  public ShoppingCartTest() {
  }

  @EJB
  static SearchFacade searchFacade;

  @EJB
  static ShoppingCart shoppingCart;

  public static void main(String[] args) {
    ShoppingCartTest shoppingCartTest = new ShoppingCartTest();
    shoppingCartTest.doTest();
  }

  void doTest() {

    try {

      System.out.println("invoking Search Facade");
      List yearWineList = searchFacade.findWineByYear(new Long(1991));
      Iterator yearit = yearWineList.iterator();

      while (yearit.hasNext()) {
        Wine wine = (Wine)yearit.next();
        System.out.println(wine.getName());
        System.out.println(wine.getYear());
      }

      List wineList = searchFacade.findAllWine();
      Iterator it = wineList.iterator();

      System.out.println("invoking shopping cart facade");
      System.out.println("success with shopping cart");

      System.out.println(shoppingCart.findCustomer("demouser@wineapp.com"));

      System.out.println("found and set customer");

      while (it.hasNext()) {
        Wine wine = (Wine)it.next();
        System.out.println(wine.getName());
        shoppingCart.addWineItem(wine, new Long(20));
        System.out.println("added cart item");
      }

      System.out.println("done with the wine loop");

      System.out.println("calling process order");
      System.out.println("Calling opc in cart");
      shoppingCart.SendOrderToOPC();
      System.out.println("done");

    }

    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
