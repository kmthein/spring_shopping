package com.shopping.service;

import com.shopping.entity.Image;

public interface ImageService {
    public Image saveImage(Image image);

    public void deleteImage(Long productId);
}
