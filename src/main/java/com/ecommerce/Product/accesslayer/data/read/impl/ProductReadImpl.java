package com.ecommerce.Product.accesslayer.data.read.impl;

import com.ecommerce.Product.accesslayer.data.read.ProductRead;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ProductReadImpl implements ProductRead {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getById(BigInteger id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }

    @Override
    public Optional<List<Product>> getAll() {
        Optional<List<Product>> products = Optional.of(productRepository.findAll());
        return products;
    }

    @Override
    public void deleteById(BigInteger id) {
        productRepository.deleteById(id);
    }
}
