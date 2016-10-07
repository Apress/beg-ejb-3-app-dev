package com.apress.ejb3.ch10.ejb30.client;

import com.apress.ejb3.ch10.ejb30.entities.Department;
import com.apress.ejb3.ch10.ejb30.entities.Employee;
import com.apress.ejb3.ch10.ejb30.hr.HRManager;
import javax.naming.Context;
import javax.naming.InitialContext;

public class HRManagerJEEClient
{
  public static void main(String[] args) {
    try {
      long ts = System.currentTimeMillis();
      final Context context = new InitialContext();
      HRManager hRManager = (HRManager)context.lookup("HRManager");
      for (Department dept: hRManager.queryDepartmentFindAll()) {
        System.out.println(dept.getDname());
        for (Employee emp: dept.getEmployeeCollection()) {
          System.out.println(' ' + emp.getEname());
        }
      }
      System.out.println("Elapsed time:  " + 
                         (System.currentTimeMillis() - ts));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
