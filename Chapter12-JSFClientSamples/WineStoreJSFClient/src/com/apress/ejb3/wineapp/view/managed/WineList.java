package com.apress.ejb3.wineapp.view.managed;

import com.apress.ejb3.chapter07.business.SearchFacade;

import com.apress.ejb3.wineapp.Wine;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import javax.naming.InitialContext;

import javax.naming.NamingException;

public class WineList
{
  public WineList() {
  }

  @EJB
  private SearchFacade searchFacade;

  private List<Wine> winesList = new ArrayList();

  private HtmlDataTable dataTable1;

  public String findAllWines() {
    InitialContext ic;
    try {
      ic = new InitialContext();
      SearchFacade searchFacade = 
        (SearchFacade)ic.lookup("com.apress.ejb3.chapter07.business.SearchFacade");
      if (searchFacade == null) {
        return "gohome";
      }
      else {
        winesList = searchFacade.findAllWine();
        return "allwines";
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    return "allwines";
  }

  public String searchByCountry() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding wineyear = app.createValueBinding("#{SearchWines.country}");
    String country = wineyear.getValue(ctx).toString();
    InitialContext ic;
    try {
      ic = new InitialContext();
      SearchFacade searchFacade = 
        (SearchFacade)ic.lookup("com.apress.ejb3.chapter07.business.SearchFacade");
      if (searchFacade == null) {
        return "gohome";
      }
      else {
        winesList = searchFacade.findWineByCountry(country);
        return "success";
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    return "success";

  }

  public String searchByVarietal() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding wineyear = app.createValueBinding("#{SearchWines.varietal}");
    String varietal = wineyear.getValue(ctx).toString();
    InitialContext ic;
    try {
      ic = new InitialContext();
      SearchFacade searchFacade = 
        (SearchFacade)ic.lookup("com.apress.ejb3.chapter07.business.SearchFacade");
      if (searchFacade == null) {
        return "gohome";
      }
      else {
        winesList = searchFacade.findWineByVarietal(varietal);
        return "success";
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    return "success";

  }

  public String searchByYear() {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding wineyear = app.createValueBinding("#{SearchWines.year}");
    String year = wineyear.getValue(ctx).toString();
    InitialContext ic;

    try {
      ic = new InitialContext();
      SearchFacade searchFacade = 
        (SearchFacade)ic.lookup("com.apress.ejb3.chapter07.business.SearchFacade");
      if (searchFacade == null) {
        return "gohome";
      }
      else {
        winesList = searchFacade.findWineByYear(new Long(year));
        return "success";
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }

    return "success";

  }

  public String invokeAddToCart() {
    Wine addWine = (Wine)this.getDataTable1().getRowData();
    FacesContext ctx = FacesContext.getCurrentInstance();
    Application app = ctx.getApplication();
    ValueBinding binding = 
      app.createValueBinding("#{JSFShoppingCart.selectedWine}");
    binding.setValue(ctx, addWine);
    return "addtocart";
  }

  public void setWinesList(List<Wine> winesList) {
    this.winesList = winesList;
  }

  public List<Wine> getWinesList() {
    return winesList;
  }

  public void setDataTable1(HtmlDataTable dataTable1) {
    this.dataTable1 = dataTable1;
  }

  public HtmlDataTable getDataTable1() {
    return dataTable1;
  }
}
