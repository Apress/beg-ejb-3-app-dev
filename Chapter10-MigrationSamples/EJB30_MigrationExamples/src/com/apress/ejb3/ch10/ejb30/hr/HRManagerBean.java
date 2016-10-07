package com.apress.ejb3.ch10.ejb30.hr;

import com.apress.ejb3.ch10.ejb30.entities.Department;
import com.apress.ejb3.ch10.ejb30.entities.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "HRManager")
public class HRManagerBean
  implements HRManager
{
  @PersistenceContext(unitName = "EJB30_MigrationExamples")
  private EntityManager em;

  private EntityManager getEntityManager() {
    //  The following code is only used when using this class directly from 
    //  a JSE client.  In this case, we are repurposing this Session bean as
    //  an ordinary JSE service bean.
    if (em == null) {
      //  Create an EntityManagerFactory using a persistence-unit that is 
      //  targeted for outside-the-container (JSE) usage
      final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("EJB30_MigrationExamples-JSE");
      em = emf.createEntityManager();
    }
    return em;
  }

  public Object mergeEntity(Object entity) {
    return getEntityManager().merge(entity);
  }

  public Object persistEntity(Object entity) {
    getEntityManager().persist(entity);
    return entity;
  }

  public Object persistEntityTX(Object entity) {
    final EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    if (tx != null) {
      try {
        tx.begin();
        em.persist(entity);
        tx.commit();
        tx = null;
        return entity;
      }
      finally {
        if (tx != null && tx.isActive()) {
          tx.rollback();
        }
      }
    }
    return null;
  }

  /** <code>select o from Department o</code> */
  public List<Department> queryDepartmentFindAll() {
    return getEntityManager().createNamedQuery("Department.findAll").getResultList();
  }

  public void removeDepartment(Department department) {
    department = 
        getEntityManager().find(Department.class, department.getDeptno());
    getEntityManager().remove(department);
  }

  /** <code>select o from Employee o</code> */
  public List<Employee> queryEmployeeFindAll() {
    return getEntityManager().createNamedQuery("Employee.findAll").getResultList();
  }

  public void removeEmployee(Employee employee) {
    employee = getEntityManager().find(Employee.class, employee.getEmpno());
    getEntityManager().remove(employee);
  }
}
