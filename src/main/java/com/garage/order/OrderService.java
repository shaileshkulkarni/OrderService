package com.garage.order;

import com.garage.messaging.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessagingService messagingService;

    public void addOrder(ItemOrder itemOrder){
        orderRepository.save(itemOrder);
        log.info("Order Added : ",itemOrder);
    }

    public void processIndividualOrder(ItemOrder itemOrder){

        messagingService.sendLocalOrder(itemOrder);

        // Update the order status
        itemOrder.setOrderStatus(ItemOrder.OrderStatus.PROCESSED);
        orderRepository.save(itemOrder);
    }

    public void processScheduledOrders(){
        // Get orders for international supplier which are not processed.
        List<ItemOrder> itemOrders = orderRepository.findByOrderTypeStatusAndSupplier(ItemOrder.OrderType.SCHEDULED, ItemOrder.OrderStatus.UNPROCESSED, ItemOrder.SupplierType.INTERNATIONAL);

        for(ItemOrder itemOrder : itemOrders){

            // Send order details to Supplier B topic
            messagingService.sendInternationalOrder(itemOrder);

            // Update the order status
            itemOrder.setOrderStatus(ItemOrder.OrderStatus.PROCESSED);
            orderRepository.save(itemOrder);
        }
    }

}
