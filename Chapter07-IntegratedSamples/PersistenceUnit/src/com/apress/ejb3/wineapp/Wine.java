package com.apress.ejb3.wineapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@NamedQueries({
  @NamedQuery(name = "findAllWine", 
              query = "select object(o) from Wine o"),
    @NamedQuery(name = "findWineByYear",
                query = "select object(wine) from Wine wine where wine.year = :year"),
    @NamedQuery(name = "findWineByCountry", 
                query = "select object(wine) from Wine wine where wine.country = :country"),
    @NamedQuery(name = "findWineByVarietal", 
                query = "select object(wine) from Wine wine where wine.varietal = :varietal")
})
@Table(name = "WINE")
public class Wine
  implements Serializable
{
  protected String country;
  protected String description;
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, 
                  generator = "TABLE_ID_GENERATOR")
  @Column(nullable = false)
  protected Long id;
  @Column(nullable = false)
  protected String name;
  protected Double rating;
  protected String region;
  @Column(name = "RETAIL_PRICE")
  protected Double retailPrice;
  protected String varietal;
  protected Long year;
  @ManyToMany
  protected Collection<Supplier> suppliers;
  @Version
  protected Integer version;

  public Wine() {
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Double getRetailPrice() {
    return retailPrice;
  }

  public void setRetailPrice(Double retailPrice) {
    this.retailPrice = retailPrice;
  }

  public String getVarietal() {
    return varietal;
  }

  public void setVarietal(String varietal) {
    this.varietal = varietal;
  }

  public Long getYear() {
    return year;
  }

  public void setYear(Long year) {
    this.year = year;
  }

  public Collection<Supplier> getSuppliers() {
    if (suppliers == null) {
      suppliers = new ArrayList();
    }
    return suppliers;
  }

  public void setSuppliers(Collection<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
}
