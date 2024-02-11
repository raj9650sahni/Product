package com.ecommerce.Product.handler;

import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface ProductHandler {

    public Product handleCreate(CreateProductRequest createProductRequest);

    public void handleUpdate(UpdateProductRequest updateProductRequest, BigInteger productId);

    public Product handleProductById(BigInteger productId);

    public void handleDeleteProductById(BigInteger productId);

    public List<Product> handleAll();

    public Product applyDiscountOrTax(BigInteger productId, BigDecimal taxPercentage, BigDecimal discount);
}
