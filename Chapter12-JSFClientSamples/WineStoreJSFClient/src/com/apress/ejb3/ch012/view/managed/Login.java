package com.apress.ejb3.ch012.view.managed;

import com.apress.ejb3.chapter07.business.ShoppingCartLocal;

import com.apress.ejb3.wineapp.Individual;

import javax.ejb.EJB;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Login
{
  public Login() {
  }
  String email;
  Individual customer;
  ShoppingCartLocal shoppingCart;

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public String processLogin() {
    String navigation = null;
    customer = shoppingCart.findCustomer(email);

    if (customer != null) {
      navigation = "winehome";
    }
    else {
      navigation = "register";
    }
    return navigation;

  }

  public void setCustomer(Individual customer) {
    this.customer = customer;
  }

  public Individual getCustomer() {
    return customer;
  }

  @EJB
  public void setShoppingCart(ShoppingCartLocal shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public ShoppingCartLocal getShoppingCart() {
    return shoppingCart;
  }
}
