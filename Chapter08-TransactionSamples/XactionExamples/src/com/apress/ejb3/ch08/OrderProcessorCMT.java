package com.apress.ejb3.ch08;

import com.apress.ejb3.wineapp.Customer;
import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.Wine;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface OrderProcessorCMT
{
  CustomerOrder createCustomerOrder(Customer customer)
    throws Exception;

  CustomerOrder createCustomerOrderUsingSupports(Customer customer)
    throws Exception;

  List<Individual> findAllCustomersByEmail(Object email);

  List<Wine> findWineByCountry(Object country);

  String initialize();

  <T> T persistEntity(T entity);
}
