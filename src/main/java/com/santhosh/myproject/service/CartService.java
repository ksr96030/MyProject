package com.santhosh.myproject.service;

import com.santhosh.myproject.model.Cart;

import java.util.List;

public interface CartService {

    Cart addToCart(Integer customerId, Integer productId, Integer quantity);

    List<Cart> getCartByCustomerId(Integer customerId);

    void removeItemFromCart(Integer cartId);

    void clearCartByCustomerId(Integer customerId);
}
