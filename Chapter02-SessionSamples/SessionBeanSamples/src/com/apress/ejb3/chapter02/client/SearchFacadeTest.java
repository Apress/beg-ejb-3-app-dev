package com.apress.ejb3.chapter02.client;

import com.apress.ejb3.chapter02.SearchFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SearchFacadeTest
{
  public SearchFacadeTest() {
  }

  public static void main(String[] args) {
    SearchFacadeTest searchFacadeTest = new SearchFacadeTest();
    searchFacadeTest.doTest();
  }

  @EJB
  static SearchFacade searchFacade;

  void doTest() {
    InitialContext ic;

    try {
      ic = new InitialContext();
      System.out.println("SearchFacade Lookup");
      System.out.println("Searching wines");
      List winesList = searchFacade.wineSearch("Red");
      System.out.println("Printing wines list");
      for (String wine: (List<String>)winesList) {
        System.out.println(wine);
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
