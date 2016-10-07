package com.apress.ejb3.ch08;

import com.apress.ejb3.wineapp.CartItem;
import com.apress.ejb3.wineapp.InventoryItem;
import com.apress.ejb3.wineapp.Wine;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal {
    void addWine(CartItem cartItem);

    void removeItem(CartItem cartItem);

    String ProcessOrder();

    List<Wine> findAllWine();

    InventoryItem findInventoryItemByWine(Wine wine);
}
