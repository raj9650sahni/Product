package com.ecommerce.Product.Adapter;

import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.model.request.CreateProductRequest;

public interface ObjectAdapter<S,T> {

    T convert(S source);


}
