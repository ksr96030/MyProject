package com.santhosh.myproject.repository;

import com.santhosh.myproject.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {
}
