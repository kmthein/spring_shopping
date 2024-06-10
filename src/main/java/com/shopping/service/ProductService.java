package com.shopping.service;

import com.shopping.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();

    public Product getProductById(Long id);

    public Product saveProduct(Product product);

    public List<Product> getProductByCategoryId(Long categoryId);

    public void deleteProduct(Long id);
}
