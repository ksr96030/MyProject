package com.santhosh.myproject.service;

import com.santhosh.myproject.DTO.CustomerDTO;
import com.santhosh.myproject.model.Customer;
import com.santhosh.myproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO login(String userName, String rawPassword) {
        Customer customer = customerRepository.findByUserName(userName);

        if (customer != null && passwordEncoder.matches(rawPassword, customer.getPassword())) {
            customer.updateLoginInfo();
            customerRepository.save(customer);

            // Convert Customer to LoginResponseDTO
            CustomerDTO responseDTO = new CustomerDTO();
            responseDTO.setUserName(customer.getUserName());
            responseDTO.setFirstName(customer.getFirstName());
            responseDTO.setLastName(customer.getLastName());
            responseDTO.setEmail(customer.getEmail());
            responseDTO.setSubscriptionType(customer.getSubscriptionType());

            return responseDTO;
        }

        return null;
    }
}
