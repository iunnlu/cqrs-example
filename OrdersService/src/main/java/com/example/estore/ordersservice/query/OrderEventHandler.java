package com.example.estore.ordersservice.query;

import com.example.estore.ordersservice.core.data.OrderEntity;
import com.example.estore.ordersservice.core.data.OrderRepository;
import com.example.estore.ordersservice.core.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderEventHandler {
    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) throws Exception {
        OrderEntity order = new OrderEntity();
        BeanUtils.copyProperties(event, order);
        orderRepository.save(order);
    }
}
