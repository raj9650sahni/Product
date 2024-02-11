package com.ecommerce.Product.service.impl;

import com.ecommerce.Product.Adapter.ProductToProductModelAdapter;
import com.ecommerce.Product.customexception.BadRequestException;
import com.ecommerce.Product.handler.ProductHandler;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.ProductModel;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;
import com.ecommerce.Product.model.response.Response;
import com.ecommerce.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service("product")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductHandler productHandler;

    private final ProductToProductModelAdapter productToProductModelAdapter;

    @Override
    public Response createProduct(CreateProductRequest productRequest) throws Exception {
        try {
            Product createdProduct = productHandler.handleCreate(productRequest);
            return new Response("success", "Product add successfully", productToProductModelAdapter.convert(createdProduct));
        } catch (BadRequestException ex) {
            throw new BadRequestException("Failed to add product: " + ex.getMessage(), ex);
        }catch (Exception ex) {
            throw new Exception("Failed to add product: " + ex.getMessage(), ex);
        }
    }


    @Override
    public Response updateProduct(UpdateProductRequest product, BigInteger productId) throws Exception {
        try {
            productHandler.handleUpdate(product, productId);
            return new Response("Success", "Product updated successfully");
        } catch (BadRequestException ex) {
            throw new BadRequestException("Failed to update product: " + ex.getMessage(), ex);
        }catch (Exception ex) {
            throw new Exception("Failed to update product: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Response getProductById(BigInteger productId){
        Product product = productHandler.handleProductById(productId);
        return new Response("Success", "Product fetched successfully", productToProductModelAdapter.convert(product));
    }

    @Override
    public Response deleteProductById(BigInteger productId) throws Exception {
        try {
            productHandler.handleDeleteProductById(productId);
            return new Response("Success", "product deleted successfully");
        } catch (BadRequestException ex) {
            throw new BadRequestException("Failed to delete product: " + ex.getMessage(), ex);
        }catch (Exception ex) {
            throw new Exception("Failed to delete product: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Response getAllProduct() throws Exception {
        try {
            List<ProductModel> productModels = productToProductModelAdapter.convert(productHandler.handleAll());
            return new Response("Success", "Product fetched successfully", productModels);
        } catch (Exception ex) {
            throw new Exception("Failed to delete product: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Response applyDiscountOrTax(BigInteger productId, BigDecimal taxPercentage, BigDecimal discount) throws Exception {
        try {
            Product modifiedProduct = productHandler.applyDiscountOrTax(productId, taxPercentage, discount);
            return new Response("Success", "Applied discount tax successfully", productToProductModelAdapter.convert(modifiedProduct));
        } catch (BadRequestException ex) {
            throw new BadRequestException("Failed to apply tax or discount on  product:  " +  ex.getMessage(), ex);
        }catch (Exception ex) {
            throw new Exception("Failed to apply tax or discount on  product:  " +  ex.getMessage(), ex);
        }
    }


}
