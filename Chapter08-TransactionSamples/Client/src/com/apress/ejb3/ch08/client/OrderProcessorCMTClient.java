package com.apress.ejb3.ch08.client;

import com.apress.ejb3.ch08.OrderProcessorCMT;
import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;
import java.sql.Timestamp;
import javax.naming.Context;
import javax.naming.InitialContext;

public class OrderProcessorCMTClient
{
  public static void main(String[] args) {
    try {
      final Context context = new InitialContext();
      OrderProcessorCMT orderProcessorCMT = 
        (OrderProcessorCMT)context.lookup("com.apress.ejb3.ch08.OrderProcessorCMT");

      //  Remove any existing Customers with email 'xaction.head@yahoo.com' and any
      //  existing Wine with country 'United States'.
      System.out.println(orderProcessorCMT.initialize());

      //  Create a Customer and add some CartItems and their associated Wines
      Individual customer = new Individual();
      customer.setFirstName("Transaction");
      customer.setLastName("Head");
      customer.setEmail("xaction.head@yahoo.com");
      for (int i = 0; i < 5; i++) {
        final Wine wine = new Wine();
        wine.setCountry("United States");
        wine.setDescription("Delicious wine");
        wine.setName("Xacti");
        wine.setRegion("Dry Creek Valley");
        wine.setRetailPrice(20.00D + i);
        wine.setVarietal("Zinfandel");
        wine.setYear(2000L + i);

        final CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        cartItem.setCustomer(customer);
        cartItem.setQuantity(12L);
        cartItem.setWine(wine);

        customer.addCartItem(cartItem);
      }

      //  Persist the Customer, relying on the cascade settings to persist all
      //  related Wine and CartItem entities as well.  Re-assign the customer,
      //  to pick up the ID value that was assigned by the EJB container when
      //  it was persisted.
      customer = orderProcessorCMT.persistEntity(customer);

      //  Create a customer order and create OrderItems from the CartItems
      final CustomerOrder customerOrder = 
        orderProcessorCMT.createCustomerOrder(customer);
      for (OrderItem orderItem: customerOrder.getOrderItemCollection()) {
        final Wine wine = orderItem.getWine();
        System.out.println(wine.getName() + " with ID " + wine.getId());
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
