package com.ecommerce.Product.model.request;

import com.ecommerce.Product.model.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductRequest extends ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

}
