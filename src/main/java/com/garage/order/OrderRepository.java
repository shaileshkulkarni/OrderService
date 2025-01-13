package com.garage.order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ItemOrder, Long> {

    public List<ItemOrder> findByOrderTypeStatusAndSupplier(ItemOrder.OrderType orderType, ItemOrder.OrderStatus orderStatus, ItemOrder.SupplierType supplierType);
}
