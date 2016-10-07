package com.apress.ejb3.wineapp;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorValue(value = "InventoryItem")
@NamedQueries( { @NamedQuery(name = "findAllInventoryItem", 
                             query = "select object(o) from InventoryItem o")
    , 
    @NamedQuery(name = "findInventoryItemByWine", query = "select object(o) from InventoryItem o where o.wine = :wine")
    } )
public class InventoryItem
  extends WineItem
  implements Serializable
{
  @Column(name = "STOCK_DATE")
  protected Timestamp stockDate;
  @Column(name = "WHOLESALE_PRICE")
  protected Double wholesalePrice;

  public InventoryItem() {
  }

  public Timestamp getStockDate() {
    return stockDate;
  }

  public void setStockDate(Timestamp stockDate) {
    this.stockDate = stockDate;
  }

  public Double getWholesalePrice() {
    return wholesalePrice;
  }

  public void setWholesalePrice(Double wholesalePrice) {
    this.wholesalePrice = wholesalePrice;
  }
}
