package com.ecommerce.Product.controller;


import com.ecommerce.Product.customexception.BadRequestException;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;
import com.ecommerce.Product.model.response.Response;
import com.ecommerce.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product-svc")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Response> createProduct(@RequestBody CreateProductRequest productRequest) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));

        }  catch (BadRequestException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failure", ex.getMessage()));
        } catch (Throwable th){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failure", th.getMessage()));
        }
    }

    @GetMapping("/fetch/product/{productId}")
    public ResponseEntity<Response> getProductById(@PathVariable BigInteger productId){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
        } catch (BadRequestException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failure", ex.getMessage()));
        } catch (Exception ex){
            Response response = new Response("failure", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/update/product/{productId}")
    public ResponseEntity<Response> updateProduct(@PathVariable BigInteger productId, @RequestBody UpdateProductRequest updateProductRequest) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(updateProductRequest, productId));
        } catch (BadRequestException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failure", ex.getMessage()));
        } catch (Exception ex){
            Response response = new Response("failure", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

    }

    @DeleteMapping("/delete/product/{productId}")
    public ResponseEntity<Response> deleteProduct(@PathVariable BigInteger productId) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProductById(productId));
        } catch (BadRequestException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failure", ex.getMessage()));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failure", ex.getMessage()));
        }
    }

    @GetMapping("/product/all")
    public ResponseEntity<Response> getAllProducts() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
        } catch (BadRequestException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failure", ex.getMessage()));
        } catch (Exception ex){
            Response response = new Response("failure", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @PutMapping("/apply/{productId}/apply-discount-or-tax")
    public ResponseEntity<Response> applyDiscountOrTax(
            @PathVariable BigInteger productId,
            @RequestParam(name = "tax_percent", required = false)
            BigDecimal taxPercentage,
            @RequestParam(name = "discount_percent", required = false)
            BigDecimal discount
    )
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.applyDiscountOrTax(productId, taxPercentage, discount));
        } catch (BadRequestException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failure", ex.getMessage()));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("failure", ex.getMessage()));
        }
    }

}
