package com.garage.order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="items_order")
public class ItemOrder {

    public enum SupplierType {
        LOCAL,
        INTERNATIONAL
    }

    public enum OrderType {
        SINGLE,
        SCHEDULED
    }

    public enum OrderStatus {
        UNPROCESSED,
        PROCESSED,
        PLACED,
        CANCELED,
        FAILED,
        SHIPPED,
        DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String itemId;
    private String itemName;
    private String supplierId;
    private SupplierType supplierType;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private double orderQuantity;
}
