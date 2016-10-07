package com.apress.ejb3.ch03;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustOrderMgrClient
{
  public static void main(String [] args) {
    try {
      final Context context = getInitialContext();
      CustOrderMgr custOrderMgr = (CustOrderMgr)context.lookup("java:comp/env/ejb/CustomerOrderManager");
      // Call any of the Remote methods below to access the EJB
      // custOrderMgr.mergeEntity(  entity );
      // custOrderMgr.persistEntity(  entity );
      System.out.println( custOrderMgr.queryCustomerFindAll(  ) );
      // custOrderMgr.removeCustomer(  customer );
      System.out.println( custOrderMgr.queryCustomerOrderFindAll(  ) );
      // custOrderMgr.removeCustomerOrder(  customerOrder );
      System.out.println( custOrderMgr.queryAddressFindAll(  ) );
      // custOrderMgr.removeAddress(  address );
      // custOrderMgr.queryCustomerFindByEmail(  email );
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static Context getInitialContext()
    throws NamingException {
    Hashtable env = new Hashtable();
    //  Standalone OC4J connection details
    env.put( Context.INITIAL_CONTEXT_FACTORY, "oracle.j2ee.naming.ApplicationClientInitialContextFactory" );
    env.put( Context.SECURITY_PRINCIPAL, "oc4jadmin" );
    env.put( Context.SECURITY_CREDENTIALS, "welcome" );
    env.put(Context.PROVIDER_URL, "ormi://localhost:23791/PersistenceSamples");
    return new InitialContext( env );
  }
}
