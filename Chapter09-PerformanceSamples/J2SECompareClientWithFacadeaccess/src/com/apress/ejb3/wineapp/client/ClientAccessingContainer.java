package com.apress.ejb3.wineapp.client;

import com.apress.ejb3.ch08.ShoppingCart;

import com.apress.ejb3.wineapp.CartItem;

import com.apress.ejb3.wineapp.Wine;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Timestamp;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientAccessingContainer
{
  public ClientAccessingContainer() {
  }

  public void PerformWineShopping(Long qty1, Long qty2) {

    try {
      ShoppingCart shop = null;
      InitialContext ic;
      try {
        ic = new InitialContext();

        shop = lookupEJB(shop, ic);
      }
      catch (NamingException e) {
        // TODO
        e.printStackTrace();
        return;
      }

      List<Wine> wineList = shop.findAllWine();
      Iterator it = wineList.iterator();
      int localCounter = 0;
      while (it.hasNext()) {
        Wine wine = (Wine)it.next();

        if (localCounter == 2 || localCounter == 5) {
          Long qty = new Long(0);
          if (localCounter == 2) {
            qty = qty1;
          }
          else {
            qty = qty2;
          }
          try {
            shop.addWineItem(wine, qty);
          }
          catch (Exception e) {
            // TODO
            e.printStackTrace();
          }

        }
        localCounter++;
      }

      shop.ProcessOrder();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private synchronized ShoppingCart lookupEJB(ShoppingCart shop, 
                                              InitialContext ic)
    throws NamingException {
    shop = (ShoppingCart)ic.lookup("com.apress.ejb3.ch08.ShoppingCart");
    return shop;
  }

}
