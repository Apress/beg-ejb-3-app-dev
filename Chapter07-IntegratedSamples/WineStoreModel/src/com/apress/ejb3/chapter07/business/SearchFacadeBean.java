package com.apress.ejb3.chapter07.business;

import com.apress.ejb3.wineapp.Wine;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "SearchFacade")
public class SearchFacadeBean
  implements SearchFacade, SearchFacadeLocal
{
  @PersistenceContext(unitName = "wineStoreUnit")
  private EntityManager em;

  public SearchFacadeBean() {
  }

  /** <code>select object(o) from Wine o</code> */
  public List<Wine> findAllWine() {
    return em.createNamedQuery("findAllWine").getResultList();
  }

  /** <code>select object(o) from Wine wine where wine.year = :year</code> */
  public List<Wine> findWineByYear(Object year) {
    return em.createNamedQuery("findWineByYear").setParameter("year", 
                                                              year).getResultList();
  }

  /** <code>select object(o) from Wine wine where wine.country = :country</code> */
  public List<Wine> findWineByCountry(Object country) {
    return em.createNamedQuery("findWineByCountry").setParameter("country", 
                                                                 country).getResultList();
  }

  /** <code>select object(o) from Wine wine where wine.varietal = :varietal</code> */
  public List<Wine> findWineByVarietal(Object varietal) {
    return em.createNamedQuery("findWineByVarietal").setParameter("varietal", 
                                                                  varietal).getResultList();
  }

  public Object mergeEntity(Object entity) {
    return em.merge(entity);
  }

  public Object persistEntity(Object entity) {
    em.persist(entity);
    return entity;
  }

  public Object refreshEntity(Object entity) {
    em.refresh(entity);
    return entity;
  }

  public void removeEntity(Object entity) {
    em.remove(em.merge(entity));
  }

  public String SendOrderToOPC() {
    return null;
  }
}
