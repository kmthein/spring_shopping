package com.shopping.service.impl;

import com.shopping.entity.Image;
import com.shopping.repository.ImageRepository;
import com.shopping.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }
}
