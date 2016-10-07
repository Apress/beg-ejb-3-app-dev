package com.apress.ejb3.ch012.view.managed;

import com.apress.ejb3.chapter07.business.ShoppingCartLocal;
import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.Wine;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

public class JSFShoppingCart
{
  public JSFShoppingCart() {
  }

  Wine selectedWine;
  String Quantity;
  ShoppingCartLocal shoppingCart;

  List<CartItem> cartItems = new ArrayList();

  public String addToCart() {

    Long qty = new Long(Quantity);
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();

    //check whether customer has already logged in.
    ValueBinding customerBinding = app.createValueBinding("#{Login.customer}");
    if (customerBinding.getValue(ctx) == null) {
      return "success";
    }
    else {

      ValueBinding shoppingCartBinding = 
        app.createValueBinding("#{Login.shoppingCart}");
      shoppingCart = (ShoppingCartLocal)shoppingCartBinding.getValue(ctx);
      shoppingCart.addWineItem(selectedWine, qty);
      return "success";
    }

  }

  public void setSelectedWine(Wine selectedWine) {
    this.selectedWine = selectedWine;
  }

  public Wine getSelectedWine() {
    return selectedWine;
  }

  public void setQuantity(String quantity) {
    this.Quantity = quantity;
  }

  public String getQuantity() {
    return Quantity;
  }

  public void setCartItems(List<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  public List<CartItem> getCartItems() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding customerBinding = app.createValueBinding("#{Login.customer}");
    Individual customer = (Individual)customerBinding.getValue(ctx);
    ValueBinding shoppingCartBinding = 
      app.createValueBinding("#{Login.shoppingCart}");
    shoppingCart = (ShoppingCartLocal)shoppingCartBinding.getValue(ctx);
    return shoppingCart.getAllCartItems(customer);
  }

  public String ProcessOrder() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding shoppingCartBinding = 
      app.createValueBinding("#{Login.shoppingCart}");
    shoppingCart = (ShoppingCartLocal)shoppingCartBinding.getValue(ctx);
    shoppingCart.SendOrderToOPC();

    return "success";
  }
}
