package com.apress.ejb3.ch08.jsfclient.managed;

import com.apress.ejb3.ch08.ShoppingCart;
import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.Wine;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class winestoreBean
{

  @PersistenceUnit(unitName = "wineStoreUnit")
  private EntityManagerFactory emf;

  private EntityManager em;

  @EJB
  private ShoppingCart shoppingCart;

  private List wineDisplayList;
  private HashMap wineHashMap = new HashMap();
  private String qty;
  private String wineKey;

  public winestoreBean() {
  }

  public void addToCart() {
    CartItem cartItem = new CartItem();
    cartItem.setQuantity(new Long(qty));
    Wine wine = (Wine)wineHashMap.get(wineKey);
    cartItem.setWine(wine);
    cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    shoppingCart.addWine(cartItem);
  }

  public String ProcessOrder() {
    shoppingCart.ProcessOrder();
    return "success";
  }

  public void setWineDisplayList(List wineDisplayList) {
    this.wineDisplayList = wineDisplayList;
  }

  public List getWineDisplayList() {
    if (wineDisplayList == null) {
      em = emf.createEntityManager();
      wineDisplayList = new ArrayList();
      for (Wine wine: 
           (List<Wine>)em.createNamedQuery("findAllWine").getResultList()) {
        SelectItem item = new SelectItem();
        String wineId = wine.getId().toString();
        String wineName = wine.getName();
        item.setLabel(wineName);
        item.setValue(wineId);
        wineHashMap.put(wineId, wine);
        wineDisplayList.add(item);
      }
      em.close();
    }

    return wineDisplayList;
  }

  public void setWineHashMap(HashMap wineHashMap) {
    this.wineHashMap = wineHashMap;
  }

  public HashMap getWineHashMap() {
    return wineHashMap;
  }

  public void setQty(String qty) {
    this.qty = qty;
  }

  public String getQty() {
    return qty;
  }

  public void setWineKey(String wineKey) {
    this.wineKey = wineKey;
  }

  public String getWineKey() {
    return wineKey;
  }
}
