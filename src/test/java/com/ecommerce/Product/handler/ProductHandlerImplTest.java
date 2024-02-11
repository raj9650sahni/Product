package com.ecommerce.Product.handler;

import com.ecommerce.Product.Adapter.RequestToProductAdapterImpl;
import com.ecommerce.Product.accesslayer.data.read.ProductRead;
import com.ecommerce.Product.accesslayer.data.write.ProductWrite;
import com.ecommerce.Product.customexception.BadRequestException;
import com.ecommerce.Product.handler.impl.ProductHandlerImpl;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.request.CreateProductRequest;
import com.ecommerce.Product.model.request.UpdateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductHandlerImplTest {

    private final RequestToProductAdapterImpl requestToProductAdapter = new RequestToProductAdapterImpl();

    @Mock
    private ProductRead productRead;

    @Mock
    private ProductWrite productWrite;

    @InjectMocks
    private ProductHandlerImpl productHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productHandler = new ProductHandlerImpl(requestToProductAdapter, productRead, productWrite);
    }

    @Test
    void testHandleCreate_Success() {
        CreateProductRequest request = new CreateProductRequest();
        request.setDescription("Chips");
        request.setProductId(BigInteger.valueOf(123));
        request.setName("Chips");
        request.setQuantity(BigDecimal.valueOf(1));
        request.setPrice(BigDecimal.valueOf(99.99));
        request.setPriceAfterDiscountAndTax(BigDecimal.valueOf(89.99));

        Product product = requestToProductAdapter.convert(request);
        when(productWrite.save(product)).thenReturn(product);

        Product createdProduct = productHandler.handleCreate(request);

        assertNotNull(createdProduct);
        verify(productWrite, times(1)).save(product);
    }

    @Test
    void testHandleCreate_Failure_InvalidRequest() {
        CreateProductRequest request = new CreateProductRequest();
        assertThrows(BadRequestException.class, () -> productHandler.handleCreate(request));
        verify(productWrite, never()).save(any());
    }

    @Test
    void testHandleUpdate_Success() {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setDescription("Aalo chips");
        request.setProductId(BigInteger.valueOf(123));
        request.setName("chips");
        request.setQuantity(BigDecimal.valueOf(1));
        request.setPrice(BigDecimal.valueOf(99.99));
        request.setPriceAfterDiscountAndTax(BigDecimal.valueOf(89.99));

        Product product = requestToProductAdapter.convert(request, BigInteger.valueOf(123));

        productHandler.handleUpdate(request, BigInteger.valueOf(123));

        verify(productWrite, times(1)).updateProduct(product);
    }

    @Test
    void testHandleUpdate_Failure_InvalidRequest() {
        UpdateProductRequest request = new UpdateProductRequest();
        BigInteger productId = BigInteger.valueOf(1);
        assertThrows(BadRequestException.class, () -> productHandler.handleUpdate(request, productId));
        verify(productWrite, never()).updateProduct(any());
    }
}
