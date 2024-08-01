package com.santhosh.myproject.service;

import com.santhosh.myproject.DTO.ProductDetailsRequest;
import com.santhosh.myproject.model.ProductDetails;

import java.io.IOException;
import java.util.List;

public interface ProductDetailsService {

    ProductDetails addProductDetails(ProductDetailsRequest productDetails) throws IOException;

    int getTotalProductsCount(Integer postedById);

    List<ProductDetails> getProductDetailsByCustomerId(Integer customerId);

    ProductDetails getProductDetailsById(Integer id);

    List<ProductDetails> getProductDetails();

    String deleteProductDetailsById(Integer id);

    ProductDetails updateProductDetailsById(Integer id, ProductDetails updatedProductDetailsData);
}
