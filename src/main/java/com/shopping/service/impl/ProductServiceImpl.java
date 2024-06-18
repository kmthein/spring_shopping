package com.shopping.service.impl;

import com.shopping.dto.CategoryDTO;
import com.shopping.dto.ImageDTO;
import com.shopping.dto.ProductDTO;
import com.shopping.entity.Category;
import com.shopping.entity.Image;
import com.shopping.entity.Product;
import com.shopping.repository.ImageRepository;
import com.shopping.repository.ProductRepository;
import com.shopping.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return convertToDTO(product);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id, List<Long> removeImgId) {
        System.out.println(product);
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (removeImgId != null && !removeImgId.isEmpty()) {
            List<Image> imgToRemove = existingProduct.getImages().stream().filter(image -> removeImgId.contains(image.getId())).collect(Collectors.toList());

            imageRepository.deleteAll(imgToRemove);

            existingProduct.getImages().removeAll(imgToRemove);
        }

        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategory(convertToDTO(product.getCategory()));
        dto.setImages(product.getImages().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return dto;
    }

    private CategoryDTO convertToDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    private ImageDTO convertToDTO(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setFileName(image.getFileName());
        dto.setFilePath(image.getFilePath());
        return dto;
    }


}
