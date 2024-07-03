package com.santhosh.myproject.dao.impl;

import com.santhosh.myproject.dao.CustomerDao;
import com.santhosh.myproject.model.Customer;
import com.santhosh.myproject.repository.CustomerRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.util.List;

@Transactional
@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public String deleteCustomerById(Integer id) {
        int deletedCount = entityManager.createQuery("Delete FROM Customer c where c.id = :customerId")
                .setParameter("customerId", id)
                .executeUpdate();
        if (deletedCount > 0) {
            return "Customer with ID " + id + " deleted successfully.";
        } else {
            return "No customer found with ID " + id + ".";
        }
    }

    @Override
    public Customer updateCustomerById(Integer id, Customer updatedCustomerData) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        if (updatedCustomerData.getFirstName() != null) {
            existingCustomer.setFirstName(updatedCustomerData.getFirstName());
        }
        if (updatedCustomerData.getLastName() != null) {
            existingCustomer.setLastName(updatedCustomerData.getLastName());
        }
        if (updatedCustomerData.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomerData.getEmail());
        }
        if (updatedCustomerData.getSubscriptionType() != null) {
            existingCustomer.setSubscriptionType(updatedCustomerData.getSubscriptionType());
        }

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return updatedCustomer;
    }

    @Override
    public Customer findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT c FROM Customer c WHERE c.userName = :username", Customer.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
