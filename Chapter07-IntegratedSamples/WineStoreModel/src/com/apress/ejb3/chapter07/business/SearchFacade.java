package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.Wine;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface SearchFacade
{

  List<Wine> findAllWine();

  List<Wine> findWineByYear(Object year);

  List<Wine> findWineByCountry(Object country);

  List<Wine> findWineByVarietal(Object varietal);

  Object mergeEntity(Object entity);

  Object persistEntity(Object entity);

  Object refreshEntity(Object entity);

  void removeEntity(Object entity);

  String SendOrderToOPC();
}
