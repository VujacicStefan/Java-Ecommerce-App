package com.example.demo.dao;

import com.example.demo.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {

    Page<Product> findAll(Pageable pageable);
    Page<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(String key1, String key2, Pageable pageable);

    Optional<Product>findByProductName(String productName);

    Optional<Product> findByProductDescription(String productDescription);

   Page<Product> findByActualPriceLessThan(Pageable pageable,String search_key);

    Page<Product> findByActualPriceGreaterThanEqual(Pageable pageable, String search_key);

}
