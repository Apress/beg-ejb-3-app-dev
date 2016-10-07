package com.apress.ejb3.ch10.ejb30.hello;

import javax.ejb.Remote;

@Remote
public interface Hello
{
  String sayHello(String name);
}
