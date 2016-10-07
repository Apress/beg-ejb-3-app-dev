package com.apress.ejb3.chapter02;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SearchFacade
{
  List wineSearch(String wineType);
}
