package com.ecommerce.Product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductModel {

    @JsonProperty("product_id")
    private BigInteger productId;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "price", required = true)
    private BigDecimal price;

    @JsonProperty(value = "total_quantity", required = true)
    private BigDecimal quantity;


    @JsonProperty("price_after_discount_and_tax")
    private BigDecimal priceAfterDiscountAndTax;
}
