package com.santhosh.myproject.service.impl;

import com.santhosh.myproject.dao.CustomerDao;
import com.santhosh.myproject.model.Customer;
import com.santhosh.myproject.repository.CustomerRepository;
import com.santhosh.myproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customRepository;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Initializing the password encoder
    }

    @Override
    public Customer create(Customer customer) {
        if (customer.getEmail() == null || customer.getFirstName() == null ||
                customer.getLastName() == null || customer.getUserName() == null ||
                customer.getPassword() == null) {
            throw new IllegalArgumentException("Missing mandatory field(s)");
        }
        if (customer.getSubscriptionType() == null) {
            customer.setSubscriptionType("free");
        }

        if (customerDao.findByUsername(customer.getUserName()) != null) {
            throw new IllegalArgumentException("Username is already taken");
        }

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);

        return customerDao.addCustomer(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public String deleteCustomerById(Integer id) {
        return customerDao.deleteCustomerById(id);
    }

    @Override
    public Customer updateCustomerById(Integer id, Customer customer) {
        return customerDao.updateCustomerById(id, customer);
    }
}
