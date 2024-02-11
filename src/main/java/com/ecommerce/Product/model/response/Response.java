package com.ecommerce.Product.model.response;


import com.ecommerce.Product.model.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response  {

    @JsonProperty("status")
    public String status;


    @JsonProperty("message")
    public String message;


    @JsonProperty("product_detail")
    public ProductModel  product;

    @JsonProperty("product_details")
    public List<ProductModel>  productModels;

    public Response (String status, String message){
        this.message = message;
        this.status = status;
    }

    public Response (String status, String message,ProductModel product){
        this.message = message;
        this.product = product;
        this.status = status;
    }

    public Response (String status, String message,List<ProductModel> productModels){
        this.message = message;
        this.productModels = productModels;
        this.status = status;
    }

}
