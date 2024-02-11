package com.ecommerce.Product.handler.impl;


import com.ecommerce.Product.Adapter.RequestToProductAdapterImpl;
import com.ecommerce.Product.accesslayer.data.read.ProductRead;
import com.ecommerce.Product.accesslayer.data.write.ProductWrite;
import com.ecommerce.Product.customexception.BadRequestException;
import com.ecommerce.Product.handler.ProductHandler;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;
import com.ecommerce.Product.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductHandlerImpl implements ProductHandler{

    private final RequestToProductAdapterImpl requestToProductAdapter;

    private final ProductRead productRead;

    private final ProductWrite productWrite;


    @Override
    public Product handleCreate(CreateProductRequest createProductRequest) {
        Product createdProduct = null;
        if (validatorRequest(createProductRequest)){
            Product product = requestToProductAdapter.convert(createProductRequest);
             createdProduct = productWrite.save(product);
        }

        return createdProduct;
    }

    @Override
    public void handleUpdate(UpdateProductRequest updateProductRequest, BigInteger productId) {
        if (validatorRequest(updateProductRequest, productId)){
            Product product = requestToProductAdapter.convert(updateProductRequest, productId);
            productWrite.updateProduct(product);
        }
    }

    @Override
    public Product handleProductById(BigInteger productId) {
        Product product = null;
        if (validatorRequest(productId)){
            Optional<Product> optionalProduct = productRead.getById(productId);
            if (optionalProduct.isPresent()){
                product = optionalProduct.get();
            }

        }
        return product;
    }

    @Override
    public void handleDeleteProductById(BigInteger productId) {
        if (validatorRequest(productId)){
            productRead.deleteById(productId);
        }
    }

    @Override
    public List<Product> handleAll() {
        Optional<List<Product>> optionalProducts = productRead.getAll();
        List<Product> productList = null;
        if (optionalProducts.isPresent()){
            productList = optionalProducts.get();
        }
        return productList;
    }

    @Override
    public Product applyDiscountOrTax(BigInteger productId, BigDecimal taxPercentage, BigDecimal discount) {
        validatorRequest(productId, taxPercentage, discount);
        Product product = handleProductById(productId);
        if (product!=null){
            if (null != discount && discount.compareTo(BigDecimal.ZERO) > 0){
                product = ProductUtil.calculateDiscount(discount, product);

            }

            if (null != taxPercentage && taxPercentage.compareTo(BigDecimal.ZERO) >0){
                product = ProductUtil.calculateTax(taxPercentage, product);
            }
            productWrite.updateProduct(product);

        }

        return product;
    }

    private boolean validatorRequest(CreateProductRequest createProductRequest) {
        if (createProductRequest == null ||
                createProductRequest.getName() == null || createProductRequest.getName().isBlank() ||
        createProductRequest.getDescription() == null || createProductRequest.getDescription().isBlank() ||
                createProductRequest.getPrice() == null || createProductRequest.getPrice().compareTo(BigDecimal.ZERO)==0 ||
                createProductRequest.getQuantity() == null || createProductRequest.getQuantity().compareTo(BigDecimal.ZERO)==0) {
            throw new BadRequestException("Invalid Request");
        }

        return true;
    }

    private boolean validatorRequest(UpdateProductRequest updateProductRequest, BigInteger productId) {
        if (productId == null || updateProductRequest == null ||
                updateProductRequest.getName() == null || updateProductRequest.getName().isBlank() ||
                updateProductRequest.getDescription() == null || updateProductRequest.getDescription().isBlank() ||
                updateProductRequest.getPrice() == null || updateProductRequest.getPrice().compareTo(BigDecimal.ZERO)==0 ||
                updateProductRequest.getQuantity() == null || updateProductRequest.getQuantity().compareTo(BigDecimal.ZERO)==0) {
            throw new BadRequestException("Invalid Request");
        }

        return true;
    }

    private boolean validatorRequest(BigInteger productId) {
        if (productId == null) {
            throw new BadRequestException("Invalid Request");
        }

        return true;
    }

    private boolean validatorRequest(BigInteger productId, BigDecimal taxPercentage, BigDecimal discount) {
        if (productId == null && (taxPercentage==null|| discount ==null)) {
            throw new BadRequestException("Invalid Request");
        }

        return true;
    }


}

