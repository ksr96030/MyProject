package com.santhosh.myproject.service.impl;

import com.santhosh.myproject.DTO.ProductDetailsRequest;
import com.santhosh.myproject.dao.ProductDetailsDao;
import com.santhosh.myproject.model.Customer;
import com.santhosh.myproject.model.ProductDetails;
import com.santhosh.myproject.service.ProductDetailsService;
import com.santhosh.myproject.service.CustomerService;
import com.santhosh.myproject.util.ImageUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    private final ProductDetailsDao productDetailsDao;

    private final CustomerService customerService;

    public ProductDetailsServiceImpl(ProductDetailsDao productDetailsDao, CustomerService customerService) {
        this.productDetailsDao = productDetailsDao;
        this.customerService = customerService;
    }

    @Override
    public ProductDetails addProductDetails(ProductDetailsRequest request) throws IOException {
        if (request.getName() == null || request.getBrand() == null ||
                request.getPrice() == null || request.getColor() == null ||
                request.getPostedBy() == null) {
            throw new IllegalArgumentException("Missing mandatory field(s)");
        }
        if(request.getCategory() == null){
            request.setCategory("Others");
        }
        if(request.getStatus() == null){
            request.setStatus("active");
        }
        if(request.getQuantity() == null){
            request.setQuantity(1);
        }
        Integer postedById = request.getPostedBy();
        Customer customer = customerService.getCustomerById(postedById);

        int currentProductsCount = getTotalProductsCount(postedById);

        String subscriptionType = customer.getSubscriptionType();
        int maxProductsAllowed = getMaxProductsAllowed(subscriptionType);

        if (currentProductsCount >= maxProductsAllowed) {
            throw new IllegalArgumentException(
                    "Product limit reached for subscription type: " + subscriptionType +
                            ". Maximum allowed: " + maxProductsAllowed
            );
        }

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName(request.getName());
        productDetails.setDescription(request.getDescription());
        productDetails.setCategory(request.getCategory());
        productDetails.setPrice(request.getPrice());
        productDetails.setQuantity(request.getQuantity());
        productDetails.setStatus(request.getStatus());
        productDetails.setPostedBy(request.getPostedBy());
        productDetails.setColor(request.getColor());
        productDetails.setBrand(request.getBrand());

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            byte[] compressedImageData = ImageUtils.compressImage(request.getImage().getBytes());
            productDetails.setImage(compressedImageData);
        }
        return productDetailsDao.addProductDetails(productDetails);
    }

    private int getMaxProductsAllowed(String subscriptionType) {
        switch (subscriptionType.toLowerCase()) {
            case "free":
                return 5;
            case "regular":
                return 10;
            case "dealer":
                return 25;
            default:
                throw new IllegalArgumentException("Invalid subscription type: " + subscriptionType);
        }
}

    @Override
    public int getTotalProductsCount(Integer postedById) {
        List<ProductDetails> customerProducts = getProductDetailsByCustomerId(postedById);
        return customerProducts.size();
    }

    @Override
    public List<ProductDetails> getProductDetailsByCustomerId(Integer customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }

        List<ProductDetails> products = productDetailsDao.findByPostedBy(customerId);

        return products;
    }


    @Override
    public ProductDetails getProductDetailsById(Integer id) {
        return productDetailsDao.getProductDetailsById(id);
    }

    @Override
    public List<ProductDetails> getProductDetails() {
        return productDetailsDao.getProductDetails();
    }

    @Override
    public String deleteProductDetailsById(Integer id) {
        return productDetailsDao.deleteProductDetailsById(id);
    }

    @Override
    public ProductDetails updateProductDetailsById(Integer id, ProductDetails updatedProductDetailsData) {
        return productDetailsDao.updateProductDetailsById(id, updatedProductDetailsData);
    }

}