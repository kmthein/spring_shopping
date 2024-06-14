package com.shopping.service.impl;

import com.shopping.entity.Image;
import com.shopping.entity.Product;
import com.shopping.repository.ImageRepository;
import com.shopping.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteImage(Long productId) {
        List<Image> image = imageRepository.findAllByProductId(productId);
        if (image.size() > 0) {
            imageRepository.deleteAllByProductId(productId);
        }
    }
}
