package com.santhosh.myproject.dao.impl;

import com.santhosh.myproject.dao.ProductDetailsDao;
import com.santhosh.myproject.model.ProductDetails;
import com.santhosh.myproject.repository.ProductDetailsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

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
    public List<ProductDetails> findByPostedBy(Integer postedBy) {
        if (postedBy == null) {
            throw new IllegalArgumentException("PostedBy ID cannot be null");
        }

        TypedQuery<ProductDetails> query = entityManager.createQuery(
                "SELECT p FROM ProductDetails p WHERE p.postedBy = :postedBy", ProductDetails.class
        );
        query.setParameter("postedBy", postedBy);

        return query.getResultList();
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

    @Override
    public void flush() {

    }

    @Override
    public <S extends ProductDetails> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProductDetails> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ProductDetails> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProductDetails getOne(Integer integer) {
        return null;
    }

    @Override
    public ProductDetails getById(Integer integer) {
        return null;
    }

    @Override
    public ProductDetails getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends ProductDetails> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ProductDetails> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ProductDetails> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ProductDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProductDetails> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProductDetails> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProductDetails, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ProductDetails> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ProductDetails> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ProductDetails> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<ProductDetails> findAll() {
        return null;
    }

    @Override
    public List<ProductDetails> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(ProductDetails entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProductDetails> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ProductDetails> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ProductDetails> findAll(Pageable pageable) {
        return null;
    }
}
