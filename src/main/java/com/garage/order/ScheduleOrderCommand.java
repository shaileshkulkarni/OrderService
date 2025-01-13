package com.garage.order;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ScheduleOrderCommand {

    private ItemOrder.OrderType orderType;
    private ItemOrder.OrderStatus orderStatus;
    private ItemOrderCommand.SupplierType supplierType;
}
