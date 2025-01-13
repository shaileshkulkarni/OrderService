package com.garage.messaging;

import com.garage.order.ItemOrder;
import com.garage.order.ItemOrderCommand;
import com.garage.order.ItemOrderCommandProcessor;
import com.garage.order.ScheduleOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class MessagingService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ItemOrderCommandProcessor itemOrderCommandProcessor;

    public void sendLocalOrder(ItemOrder itemOrder) {
        // Todo: Send order to Supplier A topic
    }

    public void sendInternationalOrder(ItemOrder itemOrder){
        // Todo: Send order to Supplier B topic
    }


    @KafkaListener(topics = "orderTopic", groupId = "orderGroup")
    public void listenItemInventoryTopic(ItemOrderCommand itemOrderCommand) {
        log.info("Received Message : " + itemOrderCommand);

        itemOrderCommandProcessor.processItemOrderCommand(itemOrderCommand);
    }

    @KafkaListener(topics = "scheduledOrderTopic", groupId = "orderGroup")
    public void listenItemInventoryTopic(ScheduleOrderCommand scheduleOrderCommand) {
        log.info("Received Message : ",scheduleOrderCommand);

        itemOrderCommandProcessor.processScheduleOrderCommand(scheduleOrderCommand);
    }
}
