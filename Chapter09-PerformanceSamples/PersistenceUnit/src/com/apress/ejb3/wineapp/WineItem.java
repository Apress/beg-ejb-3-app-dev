package com.apress.ejb3.wineapp;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("foo")
@DiscriminatorColumn(name = "TYPE", 
                     discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "findAllWineItem", 
            query = "select object(o) from WineItem o")
@Table(name = "WINE_ITEM")
public abstract class WineItem
  implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, 
                  generator = "SEQ_ID_GENERATOR")
  @Column(nullable = false)
  protected Long id;
  protected Long quantity;
  @ManyToOne
  @JoinColumn(name = "WINE_ID", referencedColumnName = "ID")
  protected Wine wine;
  @Version
  protected Integer version;

  public WineItem() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Wine getWine() {
    return wine;
  }

  public void setWine(Wine wine) {
    this.wine = wine;
  }
}
