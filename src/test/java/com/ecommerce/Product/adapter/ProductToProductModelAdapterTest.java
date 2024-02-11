package com.ecommerce.Product.adapter;

import com.ecommerce.Product.Adapter.ProductToProductModelAdapter;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductToProductModelAdapterTest {


    private  final ProductToProductModelAdapter productToProductModelAdapter = new ProductToProductModelAdapter();

    @Test
    void convertProductToModel(){

        Product product = Product.builder()
                .productId(BigInteger.valueOf(10))
                .price(BigDecimal.valueOf(100))
                .name("Shampoo Product")
                .description("dandruff shampoo")
                .quantity(BigDecimal.valueOf(1))
                .priceAfterDiscountAndTax(BigDecimal.valueOf(90))
                .build();

        ProductModel productModel = productToProductModelAdapter.convert(product);

        assertEquals(BigInteger.valueOf(10), productModel.getProductId());
        assertEquals(BigDecimal.valueOf(100), productModel.getPrice());
        assertEquals("Shampoo Product", productModel.getName());
        assertEquals("dandruff shampoo", productModel.getDescription());
        assertEquals(BigDecimal.valueOf(1), productModel.getQuantity());
        assertEquals(BigDecimal.valueOf(90), productModel.getPriceAfterDiscountAndTax());

    }

    @Test
    public void convertProductListToModelList() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .productId(BigInteger.valueOf(1))
                .price(BigDecimal.valueOf(100))
                .name("Test Product 1")
                .description("Test Description 1")
                .quantity(BigDecimal.valueOf(10))
                .priceAfterDiscountAndTax(BigDecimal.valueOf(90))
                .build());
        products.add(Product.builder()
                .productId(BigInteger.valueOf(2))
                .price(BigDecimal.valueOf(200))
                .name("Test Product 2")
                .description("Test Description 2")
                .quantity(BigDecimal.ONE)
                .priceAfterDiscountAndTax(BigDecimal.valueOf(180))
                .build());

        List<ProductModel> productModels = productToProductModelAdapter.convert(products);

        assertEquals(2, productModels.size());

        ProductModel productModel1 = productModels.get(0);
        assertEquals(BigInteger.valueOf(1), productModel1.getProductId());
        assertEquals(BigDecimal.valueOf(100), productModel1.getPrice());
        assertEquals("Test Product 1", productModel1.getName());
        assertEquals("Test Description 1", productModel1.getDescription());
        assertEquals(BigDecimal.TEN, productModel1.getQuantity());
        assertEquals(BigDecimal.valueOf(90), productModel1.getPriceAfterDiscountAndTax());


        ProductModel productModel2 = productModels.get(1);
        assertEquals(BigInteger.valueOf(2), productModel2.getProductId());
        assertEquals(BigDecimal.valueOf(200), productModel2.getPrice());
        assertEquals("Test Product 2", productModel2.getName());
        assertEquals("Test Description 2", productModel2.getDescription());
        assertEquals(BigDecimal.ONE, productModel2.getQuantity());
        assertEquals(BigDecimal.valueOf(180), productModel2.getPriceAfterDiscountAndTax());
    }
}
