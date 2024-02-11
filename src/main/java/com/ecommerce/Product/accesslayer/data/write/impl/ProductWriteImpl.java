package com.ecommerce.Product.accesslayer.data.write.impl;

import com.ecommerce.Product.accesslayer.data.write.ProductWrite;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component
@RequiredArgsConstructor
public class ProductWriteImpl implements ProductWrite {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
