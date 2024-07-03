package com.santhosh.myproject.service;

import com.santhosh.myproject.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer create(Customer customer);

    public List<Customer> getCustomers();

    public Customer getCustomerById(Integer id);

    public String deleteCustomerById(Integer id);

    Customer updateCustomerById(Integer id, Customer customer);
}
