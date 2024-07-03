package com.santhosh.myproject.controller;

import com.santhosh.myproject.DTO.ProductDetailsRequest;
import com.santhosh.myproject.model.ProductDetails;
import com.santhosh.myproject.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductDetailsController {

    @Autowired
    ProductDetailsService productDetailsService;

    @PostMapping("/store")
    public ResponseEntity<String> addProduct(@ModelAttribute ProductDetailsRequest request) {
        try {
            productDetailsService.addProductDetails(request);
            return ResponseEntity.ok("Product added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product.");
        }
    }

    @PostMapping("/update/{id}")
    public ProductDetails updateProductDetailsById(@PathVariable Integer id, @RequestBody ProductDetails updatedProductDetailsData) {
        return productDetailsService.updateProductDetailsById(id, updatedProductDetailsData);
    }

    @GetMapping("/{id}")
    public ProductDetails getProductDetailsById(@PathVariable Integer id) {
        return productDetailsService.getProductDetailsById(id);
    }

    @GetMapping("/all")
    public List<ProductDetails> getAllProductDetails() {
        return productDetailsService.getProductDetails();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductDetailsById(@PathVariable Integer id) {
        return productDetailsService.deleteProductDetailsById(id);
    }
}
