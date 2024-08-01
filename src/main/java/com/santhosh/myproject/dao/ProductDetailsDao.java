package com.santhosh.myproject.dao;

import com.santhosh.myproject.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsDao extends JpaRepository<ProductDetails, Integer> {



    ProductDetails addProductDetails(ProductDetails productDetails);

    ProductDetails getProductDetailsById(Integer id);

    List<ProductDetails> getProductDetails();

    List<ProductDetails> findByPostedBy(Integer postedBy);

    String deleteProductDetailsById(Integer id);

    ProductDetails updateProductDetailsById(Integer id, ProductDetails updatedProductDetailsData);
}
