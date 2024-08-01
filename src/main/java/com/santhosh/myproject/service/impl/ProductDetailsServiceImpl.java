package com.santhosh.myproject.service.impl;

import com.santhosh.myproject.DTO.ProductDetailsRequest;
import com.santhosh.myproject.dao.ProductDetailsDao;
import com.santhosh.myproject.model.ProductDetails;
import com.santhosh.myproject.service.ProductDetailsService;
import com.santhosh.myproject.util.ImageUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    private final ProductDetailsDao productDetailsDao;

    public ProductDetailsServiceImpl(ProductDetailsDao productDetailsDao) {
        this.productDetailsDao = productDetailsDao;
    }

    @Override
    public ProductDetails addProductDetails(ProductDetailsRequest request) throws IOException {
        if (request.getName() == null || request.getBrand() == null ||
                request.getPrice() == null || request.getColor() == null) {
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