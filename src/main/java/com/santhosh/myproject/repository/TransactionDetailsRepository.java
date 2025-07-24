package com.santhosh.myproject.repository;

import com.santhosh.myproject.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {}

