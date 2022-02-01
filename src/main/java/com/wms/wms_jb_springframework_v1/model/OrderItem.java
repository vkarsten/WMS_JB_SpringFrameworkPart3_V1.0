package com.wms.wms_jb_springframework_v1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItem {
    private String itemName;
    @NotNull
    @Min(1)
    private Integer quantity;

    public OrderItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
