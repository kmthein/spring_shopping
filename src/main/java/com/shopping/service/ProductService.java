package com.shopping.service;

import com.shopping.dto.ProductDTO;
import com.shopping.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProduct();

    public ProductDTO getProductById(Long id);

    public Product saveProduct(Product product);

    public List<Product> getProductByCategoryId(Long categoryId);

    public void deleteProduct(Long id);
}
