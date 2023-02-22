package com.example.estore.productsservice.query;

import com.example.estore.core.events.ProductReservationCancelEvent;
import com.example.estore.core.events.ProductReservedEvent;
import com.example.estore.productsservice.core.data.ProductEntity;
import com.example.estore.productsservice.core.data.ProductsRepository;
import com.example.estore.productsservice.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@ProcessingGroup("product-group")
public class ProductsEventHandler {
    private final ProductsRepository productsRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsEventHandler.class);

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        ProductEntity product = new ProductEntity();
        BeanUtils.copyProperties(event, product);
        productsRepository.save(product);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
        productsRepository.save(productEntity);

        LOGGER.info("ProductReservedEvent is called for productId: " + productReservedEvent.getProductId() + " and orderId: " + productReservedEvent.getOrderId());
    }

    @EventHandler
    public void on(ProductReservationCancelEvent productReservationCancelEvent) {
        ProductEntity productEntity = productsRepository.findByProductId(productReservationCancelEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() + productReservationCancelEvent.getQuantity());
        productsRepository.save(productEntity);

        LOGGER.info("ProductReservedEvent is called for productId: " + productReservationCancelEvent.getProductId() + " and orderId: " + productReservationCancelEvent.getOrderId());
    }
}