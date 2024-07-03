package com.santhosh.myproject.dao;

import com.santhosh.myproject.model.ProductDetails;

import java.util.List;

public interface ProductDetailsDao {


    ProductDetails addProductDetails(ProductDetails productDetails);

    ProductDetails getProductDetailsById(Integer id);

    List<ProductDetails> getProductDetails();

    String deleteProductDetailsById(Integer id);

    ProductDetails updateProductDetailsById(Integer id, ProductDetails updatedProductDetailsData);
}
