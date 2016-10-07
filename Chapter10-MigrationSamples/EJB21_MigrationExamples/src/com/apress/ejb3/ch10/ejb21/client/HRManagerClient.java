package com.apress.ejb3.ch10.ejb21.client;

import com.apress.ejb3.ch10.ejb21.entities.DepartmentLocalDTO;
import com.apress.ejb3.ch10.ejb21.entities.EmployeeLocalDTO;
import com.apress.ejb3.ch10.ejb21.hr.HRManager;
import com.apress.ejb3.ch10.ejb21.hr.HRManagerHome;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class HRManagerClient
{
  public static void main(String[] args) {
    try {
      long ts = System.currentTimeMillis();
      final Context context = new InitialContext();
      final HRManagerHome hRManagerHome = 
        (HRManagerHome)PortableRemoteObject.narrow(context.lookup("HRManager21"), 
                                                   HRManagerHome.class);
      HRManager hRManager;
      hRManager = hRManagerHome.create();
      for (DepartmentLocalDTO dept: hRManager.retrieveAllDepartment()) {
        System.out.println(dept.getDname());
        for (EmployeeLocalDTO emp: dept.getEmployeesDTO()) {
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
