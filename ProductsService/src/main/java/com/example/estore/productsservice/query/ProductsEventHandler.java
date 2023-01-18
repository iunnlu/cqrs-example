package com.example.estore.productsservice.query;

import com.example.estore.productsservice.core.data.ProductEntity;
import com.example.estore.productsservice.core.data.ProductsRepository;
import com.example.estore.productsservice.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@ProcessingGroup("product-group")
public class ProductsEventHandler {
    private final ProductsRepository productsRepository;

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
}