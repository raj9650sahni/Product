package com.ecommerce.Product.service;

import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.ProductModel;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;
import com.ecommerce.Product.model.response.Response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface ProductService {

    public Response createProduct(CreateProductRequest productRequest) throws Exception;

    public Response updateProduct(UpdateProductRequest product, BigInteger productId) throws Exception;

    public Response getProductById(BigInteger productId);

    public Response deleteProductById(BigInteger productId) throws Exception;

    public Response getAllProduct() throws Exception;

    public Response applyDiscountOrTax(BigInteger productId, BigDecimal taxPercentage, BigDecimal discount) throws Exception;


}
