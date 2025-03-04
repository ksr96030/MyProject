package com.santhosh.myproject.repository;

import com.santhosh.myproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomerId(Integer customerId);
}
