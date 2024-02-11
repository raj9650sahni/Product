package com.ecommerce.Product.Adapter;

import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductToProductModelAdapter implements ObjectAdapter<Product, ProductModel> {
    @Override
    public ProductModel convert(Product product) {
        return ProductModel.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .priceAfterDiscountAndTax(product.getPriceAfterDiscountAndTax())
                .build();
    }

    public List<ProductModel> convert(List<Product> products) {
        List<ProductModel> productModels = new ArrayList<>();
        for (Product product : products) {
            ProductModel productModel = ProductModel.builder()
                    .productId(product.getProductId())
                    .name(product.getName())
                    .quantity(product.getQuantity())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .priceAfterDiscountAndTax(product.getPriceAfterDiscountAndTax())
                    .build();
            productModels.add(productModel);

        }

        return productModels;
    }

}
