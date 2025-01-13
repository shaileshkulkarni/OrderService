package com.garage.order;


public class ItemOrderCommand {

    public enum SupplierType {
        LOCAL,
        INTERNATIONAL
    }

    private String itemId;

    private String itemName;
    private String supplierId;
    private ItemOrder.SupplierType supplierType;
    private double orderQuantity;
}
