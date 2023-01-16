package com.example.estore.productsservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId);
}
