package com.apress.ejb3.ch08.client;

import com.apress.ejb3.ch08.OrderProcessorBMT;
import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.OrderItem;
import com.apress.ejb3.wineapp.Wine;
import java.sql.Timestamp;
import javax.naming.Context;
import javax.naming.InitialContext;

public class OrderProcessorBMTClient
{
  /**
   * The OrderProcessorBMTClient creates a CustomerOrder from a Customer's
   * CartItem list.  It makes explicit calls to the OrderProcessorBMT class to 
   * demarcate the transaction, and demonstrates cancelling the order
   * after it has been created.
   * 
   * @param args
   */
  public static void main(String[] args) {
    OrderProcessorBMT orderProcessorBMT = null;
    try {
      final Context context = new InitialContext();

      //  Lookup our OrderProcessorBMT service (Stateful Session) bean
      orderProcessorBMT = 
          (OrderProcessorBMT)context.lookup("com.apress.ejb3.ch08.OrderProcessorBMT");

      //  Begin a new transaction for removing any pre-existing test data
      orderProcessorBMT.beginTrans();

      //  Remove any existing Customers with email 'xaction.head@yahoo.com'
      for (Individual customer: 
           orderProcessorBMT.findAllCustomersByEmail("xaction.head@yahoo.com")) {
        orderProcessorBMT.removeEntity(customer);
      }

      //  Remove any existing Wine with country 'United States'
      for (Wine wine: orderProcessorBMT.findWineByCountry("United States")) {
        orderProcessorBMT.removeEntity(wine);
      }

      //  Apply these changes, committing the entity removal operations
      orderProcessorBMT.commitTrans();

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

      //  Begin a new transaction to perform the order processing steps
      orderProcessorBMT.beginTrans();

      //  Persist the Customer, relying on the cascade settings to persist all
      //  related Wine and CartItem entities as well.  Re-assign the customer,
      //  to pick up the ID value that was assigned by the EJB container when
      //  it was persisted.
      customer = orderProcessorBMT.persistEntity(customer);

      //  Create a customer order and create OrderItems from the CartItems
      CustomerOrder customerOrder = 
        orderProcessorBMT.createCustomerOrder(customer);

      //  Query the Wines in the CustomerOrder
      for (OrderItem orderItem: customerOrder.getOrderItemCollection()) {
        final Wine wine = orderItem.getWine();
        System.out.println(wine.getName() + " with ID " + wine.getId());
      }

      //  Commit the order, applying all of the changes made thus far      
      orderProcessorBMT.commitTrans();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      if (orderProcessorBMT != null) {
        try {
          orderProcessorBMT.rollbackTrans();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
