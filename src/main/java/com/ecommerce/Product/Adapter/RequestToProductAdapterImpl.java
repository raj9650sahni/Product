package com.ecommerce.Product.Adapter;

import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class RequestToProductAdapterImpl implements ObjectAdapter<CreateProductRequest, Product>{
    @Override
    public Product convert(CreateProductRequest createProductRequest) {
        return Product.builder()
                .price(createProductRequest.getPrice())
                .description(createProductRequest.getDescription())
                .name(createProductRequest.getName())
                .quantity(createProductRequest.getQuantity())
                .build();
    }

    public Product convert(UpdateProductRequest updateProductRequest, BigInteger productId) {
        return Product.builder()
                .productId(productId)
                .price(updateProductRequest.getPrice())
                .description(updateProductRequest.getDescription())
                .name(updateProductRequest.getName())
                .quantity(updateProductRequest.getQuantity())
                .build();
    }

}
