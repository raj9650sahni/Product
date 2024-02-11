package com.ecommerce.Product.accesslayer.data.read;

import com.ecommerce.Product.model.Product;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProductRead {

    public Optional<Product> getById(BigInteger id);

    public Optional<List<Product>> getAll();

    public void deleteById(BigInteger id);
}
