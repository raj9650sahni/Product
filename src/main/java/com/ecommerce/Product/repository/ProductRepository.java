package com.ecommerce.Product.repository;

import com.ecommerce.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface ProductRepository  extends JpaRepository<Product, BigInteger> {

}
