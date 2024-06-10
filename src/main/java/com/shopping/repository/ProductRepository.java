package com.shopping.repository;

import com.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);

    @Query(value = "SELECT p.* FROM product p " +
            "INNER JOIN category c ON c.id = p.category_id " +
            "INNER JOIN image i ON i.product_id = p.id",
            nativeQuery = true)
    List<Product> findAllByImages();
}
