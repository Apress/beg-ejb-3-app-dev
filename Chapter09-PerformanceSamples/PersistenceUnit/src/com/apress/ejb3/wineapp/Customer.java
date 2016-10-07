package com.apress.ejb3.wineapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorValue("foo")
@NamedQueries( { @NamedQuery(name = "findAllCustomer", 
                             query = "select object(o) from Customer o")
    , 
    @NamedQuery(name = "findCustomerByEmailInCustomer", query = "select object(cust) from Customer cust where cust.email = :email")
    } )
@Table(name = "CUSTOMER")
public abstract class Customer
  extends BusinessContact
  implements Serializable
{

  protected String email;

  @OneToMany(mappedBy = "customer", cascade = { CascadeType.ALL })
  protected Collection<CustomerOrder> customerOrderCollection;

  @ManyToOne
  @JoinColumn(name = "BILLING_ADDRESS", referencedColumnName = "ID")
  protected Address billingAddress;

  @OneToMany(mappedBy = "customer", 
             cascade = { CascadeType.REMOVE, CascadeType.ALL })
  protected Collection<CartItem> cartItemCollection;

  @ManyToOne
  @JoinColumn(name = "SHIPPING_ADDRESS", referencedColumnName = "ID")
  protected Address shippingAddress;

  //  @OneToMany(cascade = { CascadeType.ALL })
  //  protected Collection<Address> addresses;

  public Customer() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Collection<CustomerOrder> getCustomerOrderCollection() {
    if (customerOrderCollection == null) {
      customerOrderCollection = new ArrayList();
    }
    return customerOrderCollection;
  }

  public void setCustomerOrderCollection(Collection<CustomerOrder> customerOrderCollection) {
    this.customerOrderCollection = customerOrderCollection;
  }

  public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderCollection().add(customerOrder);
    customerOrder.setCustomer(this);
    return customerOrder;
  }

  public CustomerOrder removeCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderCollection().remove(customerOrder);
    customerOrder.setCustomer(null);
    return customerOrder;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address address) {
    this.billingAddress = address;
  }

  public Collection<CartItem> getCartItemCollection() {
    if (cartItemCollection == null) {
      cartItemCollection = new ArrayList();
    }

    return cartItemCollection;
  }

  public void setCartItemCollection(Collection<CartItem> cartItemCollection) {
    this.cartItemCollection = cartItemCollection;
  }

  public CartItem addCartItem(CartItem cartItem) {
    getCartItemCollection().add(cartItem);
    cartItem.setCustomer(this);
    return cartItem;
  }

  public CartItem removeCartItem(CartItem cartItem) {
    getCartItemCollection().remove(cartItem);
    return cartItem;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address address1) {
    this.shippingAddress = address1;
  }
}
