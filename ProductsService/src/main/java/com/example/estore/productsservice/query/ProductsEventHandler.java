package com.example.estore.productsservice.query;

import com.example.estore.productsservice.core.data.ProductEntity;
import com.example.estore.productsservice.core.data.ProductsRepository;
import com.example.estore.productsservice.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductsEventHandler {
    private final ProductsRepository productsRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity product = new ProductEntity();
        BeanUtils.copyProperties(event, product);
        productsRepository.save(product);
    }
}
