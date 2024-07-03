package com.santhosh.myproject.dao;

import com.santhosh.myproject.model.Customer;

import java.util.List;

public interface CustomerDao {
   public String deleteCustomerById(Integer id);

   Customer updateCustomerById(Integer id, Customer updatedCustomerData);

   Customer addCustomer(Customer customer);

   List<Customer> getCustomers();

   Customer getCustomerById(Integer id);

   Customer findByUsername(String userName);
}
