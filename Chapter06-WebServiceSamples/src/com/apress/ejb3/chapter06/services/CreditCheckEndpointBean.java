package com.apress.ejb3.chapter06.services;

import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless(name = "CreditCheckEndpointBean")
@WebService(serviceName = "CreditService", 
            targetNamespace = "http://www.apress.com/ejb3/credit")
public class

CreditCheckEndpointBean
{

  public CreditCheckEndpointBean() {
  }

  @WebMethod(operationName = "CreditCheck")
  public

  boolean validateCC(String cc) {
    return true;
  }

}
