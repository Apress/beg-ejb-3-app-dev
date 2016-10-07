package com.apress.ejb3.ch04.mappedsuperclass.client;

import com.apress.ejb3.ch04.mappedsuperclass.Address;
import com.apress.ejb3.ch04.mappedsuperclass.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityClient
{
  private static final String PERSISTENCE_UNIT = "MappedSuperclass-JSE";

  public static void main(String[] args) {
    final EntityManagerFactory emf = 
      Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    final EntityManager em = emf.createEntityManager();
    final EntityTransaction et = em.getTransaction();

    //  Create some entities and wire them together
    final Employee emp = new Employee();
    emp.setFirstName("Jon");
    emp.setLastName("Wetherbee");
    emp.setEmail("laterdaze@wayzout.com");
    emp.setDept("Magistry");

    final Address address = new Address();
    address.setCity("San Mateo");
    address.setState("CA");
    address.setStreet1("28 Barbary Lane");
    address.setZipCode(94402L);

    emp.setHomeAddress(address);

    //  Perform a 'persist' operation inside a transaction.  Note that no 
    //  transaction context was required before this, during entity creation 
    //  and association.  Also note that we have set up 'cascade' settings on
    //  all relationships, so persisting our FullTimeEmployee causes a cascading
    //  persist of its related PartTimeEmployee and Address entities.
    try {
      et.begin();
      em.persist(emp);
      et.commit();
    }
    catch (Exception e) {
      if (et.isActive()) {
        et.rollback();
      }
    }

    //  Now we query back all FullTimeEmployee entities, and print out some 
    //  details
    final List<Employee> emps = 
      em.createNamedQuery("Employee.findAll").getResultList();
    for (Employee e: emps) {
      System.out.println(e.getFirstName());
      System.out.println(e.getLastName());
      System.out.println(e.getEmail());

      final Address a = e.getHomeAddress();

      System.out.println(a.getStreet1());
      System.out.println(a.getCity());
      System.out.println(a.getState());
      System.out.println(a.getZipCode());
    }
  }
}
