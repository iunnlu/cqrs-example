package com.example.estore.productsservice.core.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name="products")
@Entity
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = -7870664933635954570L;

    @Id
    private String productId;
    @Column(unique = true)
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
