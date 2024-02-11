package com.ecommerce.Product.accesslayer.data.write;

import com.ecommerce.Product.model.Product;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProductWrite {

    public Product save(Product product);

    public void updateProduct(Product product);
}
