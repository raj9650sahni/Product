package com.ecommerce.Product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "original_price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_quantity", nullable = false)
    private BigDecimal quantity;


    @Column(name = "price_after_discount_and_tax", nullable = true)
    private BigDecimal priceAfterDiscountAndTax;
}
