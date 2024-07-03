package com.santhosh.myproject.controller;

import com.santhosh.myproject.model.Customer;
import com.santhosh.myproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/store")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable Integer id) {
        return customerService.deleteCustomerById(id);
    }

    @PostMapping("/update/{id}")
    public Customer updateCustomerById(@PathVariable Integer id, @RequestBody Customer customer) {
        return customerService.updateCustomerById(id, customer);
    }
}
