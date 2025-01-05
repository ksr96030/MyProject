package com.santhosh.myproject.repository;

import com.santhosh.myproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUserName(String userName);
}
