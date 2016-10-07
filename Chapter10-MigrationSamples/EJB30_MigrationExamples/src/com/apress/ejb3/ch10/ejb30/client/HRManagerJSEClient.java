package com.apress.ejb3.ch10.ejb30.client;

import com.apress.ejb3.ch10.ejb30.entities.Department;
import com.apress.ejb3.ch10.ejb30.entities.Employee;
import com.apress.ejb3.ch10.ejb30.hr.HRManagerBean;

public class HRManagerJSEClient
{
  public static void main(String[] args) {
    long ts = System.currentTimeMillis();
    //  Repurpose our HRManagerBean class as a POJO service class -- its
    //  injection code is ignored, and it relies instead on an 
    //  EntityManagerFactory to create its EntityManager instance.
    final HRManagerBean svc = new HRManagerBean();
    for (Department dept: svc.queryDepartmentFindAll()) {
      System.out.println(dept.getDname());
      for (Employee emp: dept.getEmployeeCollection()) {
        System.out.println(' ' + emp.getEname());
      }
    }
    System.out.println("Elapsed time:  " + (System.currentTimeMillis() - ts));
  }
}
