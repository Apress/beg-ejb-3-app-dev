package com.apress.ejb3.chapter06.client;

import javax.xml.ws.WebServiceRef;
import com.apress.ejb3.credit.CreditCheckEndpointBean;
import com.apress.ejb3.credit.CreditService;

public class CreditServiceClient
{
  public CreditServiceClient() {
  }

  @WebServiceRef(wsdlLocation = 
                 "http://localhost:8080/CreditService/CreditCheckEndpointBean?WSDL")
  static CreditService service;

  public static void main(String[] args) {
    CreditServiceClient creditServiceClient = new CreditServiceClient();
    creditServiceClient.testCreditService();
  }

  public void testCreditService() {
    try {

      CreditCheckEndpointBean creditService = 
        service.getCreditCheckEndpointBeanPort();
      System.out.println(creditService.creditCheck("12345678"));

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
