package com.ecommerce.Product.util;

import com.ecommerce.Product.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@Slf4j
public class ProductUtil {

    public static Product calculateTax(BigDecimal taxPercentage, Product product){
        BigDecimal quantity = product.getQuantity();
        BigDecimal amountTaxToBeCalculatedOn = product.getPriceAfterDiscountAndTax()!= null ? product.getPriceAfterDiscountAndTax() : product.getPrice();
        BigDecimal taxOnSingleItem = taxPercentage.divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING)
                .multiply(amountTaxToBeCalculatedOn);

        BigDecimal priceAfterTax = amountTaxToBeCalculatedOn.add(taxOnSingleItem);
        product.setPriceAfterDiscountAndTax(priceAfterTax);
        return product;


    }

    public static Product calculateDiscount(BigDecimal discount, Product product){
        BigDecimal quantity = product.getQuantity();
        BigDecimal discountOnSingleItem = discount.divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING)
                .multiply(product.getPrice());

        BigDecimal priceAfterDiscount = product.getPrice().subtract(discountOnSingleItem);
        product.setPriceAfterDiscountAndTax(priceAfterDiscount);
        return product;

    }
}
