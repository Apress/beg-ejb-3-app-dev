package com.apress.ejb3.ch10.ejb30.hello;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless(name = "Hello")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class // Default value
HelloBean
  implements Hello, HelloWebService
{
  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
  public // Override
  String sayHello(String name) {
    return "Hello " + name;
  }
}
