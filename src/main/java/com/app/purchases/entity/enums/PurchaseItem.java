package com.app.purchases.entity.enums;

import com.app.purchases.exception.NoSuchPurchaseItemException;

public enum PurchaseItem {
    TV, SMARTPHONE, JUICER, HEADPHONES, KEYBOARD;

    public static PurchaseItem of(String value){
        for(PurchaseItem item : PurchaseItem.values()){
            if(item.name().equals(value.toUpperCase())){
                return item;
            }
        }
        throw new NoSuchPurchaseItemException(String.format("Item '%s' not found", value));
    }
}
