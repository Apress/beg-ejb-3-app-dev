package com.apress.ejb3.ch08;

import com.apress.ejb3.wineapp.Customer;
import com.apress.ejb3.wineapp.CustomerOrder;
import com.apress.ejb3.wineapp.Individual;
import com.apress.ejb3.wineapp.Wine;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface OrderProcessorBMT
{
  <T> T mergeEntity(T entity);

  <T> T persistEntity(T entity);

  <T> T refreshEntity(T entity);

  void removeEntity(Object entity);

  CustomerOrder createCustomerOrder(Customer customer)
    throws Exception;

  void beginTrans()
    throws Exception;

  void commitTrans()
    throws Exception;

  void rollbackTrans()
    throws Exception;

  List<Customer> findAllCustomer();

  List<Customer> findCustomerByEmailInCustomer(Object email);

  List<Individual> findAllIndividual();

  List<Individual> findAllCustomersByEmail(Object email);

  List<Individual> findCustomerByLastName(Object lastName);

  List<Wine> findAllWine();

  List<Wine> findWineByYear(Object year);

  List<Wine> findWineByCountry(Object country);

  List<Wine> findWineByVarietal(Object varietal);
}
