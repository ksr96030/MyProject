package com.santhosh.myproject.controller;

import com.santhosh.myproject.DTO.CartRequest;
import com.santhosh.myproject.model.Cart;
import com.santhosh.myproject.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody CartRequest request) {
        Cart cart = cartService.addToCart(request.getCustomerId(), request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Cart>> getCartByCustomerId(@PathVariable Integer customerId) {
        List<Cart> cartItems = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Integer cartId) {
        cartService.removeItemFromCart(cartId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("/clear/{customerId}")
    public ResponseEntity<String> clearCart(@PathVariable Integer customerId) {
        cartService.clearCartByCustomerId(customerId);
        return ResponseEntity.ok("Cart cleared");
    }
}
