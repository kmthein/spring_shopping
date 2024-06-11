package com.shopping.repository;

import com.shopping.entity.Image;
import com.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByProductId(Long productId);

    void deleteAllByProductId(Long productId);
}
