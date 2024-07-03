package com.santhosh.myproject.dao.impl;

import com.santhosh.myproject.dao.ProductDetailsDao;
import com.santhosh.myproject.model.ProductDetails;
import com.santhosh.myproject.repository.ProductDetailsRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Transactional
@Repository
public class ProductDetailsDaoImpl implements ProductDetailsDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Override
    public ProductDetails addProductDetails(ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }

    @Override
    public ProductDetails getProductDetailsById(Integer id) {
        return productDetailsRepository.findById(id).get();
    }

    @Override
    public List<ProductDetails> getProductDetails() {
        return productDetailsRepository.findAll();
    }

    @Override
    public String deleteProductDetailsById(Integer id) {
        int deletedCount = entityManager.createQuery("Delete FROM ProductDetails p where p.id = :productDetailsId")
                .setParameter("productDetailsId", id)
                .executeUpdate();
        if (deletedCount > 0) {
            return "ProductDetails with ID " + id + " deleted successfully.";
        } else {
            return "No productDetails found with ID " + id + ".";
        }
    }

    @Override
    public ProductDetails updateProductDetailsById(Integer id, ProductDetails updatedProductDetailsData) {
        ProductDetails existingProductDetails = productDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDetails not found with id: " + id));

        if (updatedProductDetailsData.getName() != null) {
            existingProductDetails.setName(updatedProductDetailsData.getName());
        }
        if (updatedProductDetailsData.getPrice() != null) {
            existingProductDetails.setPrice(updatedProductDetailsData.getPrice());
        }

        if (updatedProductDetailsData.getQuantity() != null) {
            existingProductDetails.setQuantity(updatedProductDetailsData.getQuantity());
        }

        if (updatedProductDetailsData.getDescription() != null) {
            existingProductDetails.setDescription(updatedProductDetailsData.getDescription());
        }

        if (updatedProductDetailsData.getImage() != null) {
            existingProductDetails.setImage(Arrays.toString(updatedProductDetailsData.getImage()).getBytes());
        }

        if (updatedProductDetailsData.getCategory() != null) {
            existingProductDetails.setCategory(updatedProductDetailsData.getCategory());
        }
        if (updatedProductDetailsData.getColor() != null) {
            existingProductDetails.setColor(updatedProductDetailsData.getColor());
        }

        if (updatedProductDetailsData.getBrand() != null) {
            existingProductDetails.setBrand(updatedProductDetailsData.getBrand());
        }
        return productDetailsRepository.save(existingProductDetails);

    }
}
