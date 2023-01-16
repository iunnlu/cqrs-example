package com.example.estore.productsservice.query;

import com.example.estore.productsservice.core.data.ProductEntity;
import com.example.estore.productsservice.core.data.ProductsRepository;
import com.example.estore.productsservice.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductsQueryHandler {
    private final ProductsRepository productsRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductRestModel> productRestModels = new ArrayList<>();
        List<ProductEntity> productEntities = productsRepository.findAll();
        for(ProductEntity productEntity: productEntities) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRestModels.add(productRestModel);
        }
        return productRestModels;
    }
}
