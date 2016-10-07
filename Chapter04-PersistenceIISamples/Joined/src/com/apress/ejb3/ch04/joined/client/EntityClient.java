package com.apress.ejb3.ch04.joined.client;

import com.apress.ejb3.ch04.joined.Address;
import com.apress.ejb3.ch04.joined.Employee;
import com.apress.ejb3.ch04.joined.FullTimeEmployee;
import com.apress.ejb3.ch04.joined.PartTimeEmployee;
import com.apress.ejb3.ch04.joined.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityClient
{
  private static final String PERSISTENCE_UNIT = "Joined-JSE";

  public static void main(String[] args) {
    //  Use the EntityManagerFactory to obtain an EntityManager for 
    //  PERSISTENCE_UNIT.  From the EntityManager, acquire an EntityTransaction
    //  instance.
    final EntityManagerFactory emf = 
      Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    final EntityManager em = emf.createEntityManager();
    final EntityTransaction et = em.getTransaction();

    //  Clear out any previous test data.  Due to 'cascade' settings on the
    //  'homeAddress' relationship field, removing a Person will remove its 
    //  Address as well.
    final List<Person> people = 
      em.createNamedQuery("Person.findAll").getResultList();

    try {
      et.begin();
      for (Person person: people) {
        System.out.println("Removing " + person.getLastName());
        em.remove(person);
      }
      et.commit();
    }
    catch (Exception e) {
      if (et.isActive()) {
        et.rollback();
      }
    }

    //  Create some entities and wire them together
    final FullTimeEmployee ftEmp = new FullTimeEmployee();
    ftEmp.setFirstName("Jon");
    ftEmp.setLastName("Wetherbee");
    ftEmp.setEmail("laterdaze@wayzout.com");
    ftEmp.setAnnualSalary(123.45);
    ftEmp.setDept("Magistry");

    final Address ftAddress = new Address();
    ftAddress.setCity("San Mateo");
    ftAddress.setState("CA");
    ftAddress.setStreet1("28 Barbary Lane");
    ftAddress.setZipCode(94402L);

    ftEmp.setHomeAddress(ftAddress);

    final PartTimeEmployee ptEmp = new PartTimeEmployee();
    ptEmp.setFirstName("John Paul");
    ptEmp.setLastName("Jones");
    ptEmp.setEmail("led@zep.com");

    ftEmp.addEmployee(ptEmp);

    //  Perform a 'persist' operation inside a transaction.  Note that no 
    //  transaction context was required before this, during entity creation 
    //  and association.  Also note that we have set up 'cascade' settings on
    //  all relationships, so persisting our FullTimeEmployee causes a cascading
    //  persist of its related PartTimeEmployee and Address entities.
    try {
      et.begin();
      em.persist(ftEmp);
      et.commit();
    }
    catch (Exception e) {
      if (et.isActive()) {
        et.rollback();
      }
    }

    //  Now we query back all FullTimeEmployee entities, and print out some 
    //  details
    final List<FullTimeEmployee> ftEmps = 
      em.createNamedQuery("FullTimeEmployee.findAll").getResultList();
    for (FullTimeEmployee mgr: ftEmps) {
      System.out.println(mgr.getFirstName());
      System.out.println(mgr.getLastName());
      System.out.println(mgr.getEmail());

      //  Traverse the managedEmployees relationship field
      for (Employee emp: mgr.getManagedEmployees()) {
        System.out.println(emp.getFirstName());
        System.out.println(emp.getLastName());
        System.out.println(emp.getEmail());

        //  Traverse back through the manager relationship field to its 
        //  homeAddress relationship field, and print some its fields
        final Address mgrHomeAddress = emp.getManager().getHomeAddress();
        System.out.println(mgrHomeAddress.getStreet1());
        System.out.println(mgrHomeAddress.getCity());
        System.out.println(mgrHomeAddress.getState());
        System.out.println(mgrHomeAddress.getZipCode());
      }
    }
  }
}
