package com.santhosh.myproject.service.impl;

import com.santhosh.myproject.model.Cart;
import com.santhosh.myproject.model.Customer;
import com.santhosh.myproject.model.ProductDetails;
import com.santhosh.myproject.repository.CartRepository;
import com.santhosh.myproject.service.CartService;
import com.santhosh.myproject.service.CustomerService;
import com.santhosh.myproject.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductDetailsService productDetailsService;

    @Override
    public Cart addToCart(Integer customerId, Integer productId, Integer quantity) {
        Customer customer = customerService.getCustomerById(customerId);
        ProductDetails product = productDetailsService.getProductDetailsById(productId);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setDateAdded(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartByCustomerId(Integer customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    @Override
    public void removeItemFromCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void clearCartByCustomerId(Integer customerId) {
        List<Cart> cartItems = cartRepository.findByCustomerId(customerId);
        cartRepository.deleteAll(cartItems);
    }
}
