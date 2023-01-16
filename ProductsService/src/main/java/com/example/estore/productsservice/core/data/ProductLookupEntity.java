package com.example.estore.productsservice.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="product_lookup")
public class ProductLookupEntity implements Serializable {
    private static final long serialVersionUID = -6102325940911215875L;
    @Id
    private String productId;
    @Column(unique = true)
    private String title;
}
