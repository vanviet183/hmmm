package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.applications.repositories.ImageRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.services.ImageService;
import com.hit.product.applications.utils.UploadFile;
import com.hit.product.domains.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UploadFile uploadFile;

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        checkImageException(image);
        return image.get();
    }

    @Override
    public Image updateImage(Long id, MultipartFile multipartFile) {
        Optional<Image> image = imageRepository.findById(id);
        checkImageException(image);
        if(image.get().getImageUrl() != null) {
            uploadFile.removeFileFromUrl(image.get().getImageUrl());
        }
        image.get().setImageUrl(uploadFile.getUrlFromFile(multipartFile));
        return imageRepository.save(image.get());
    }

    @Override
    public TrueFalseResponse deleteImage(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        checkImageException(image);
        imageRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkImageException(Optional<Image> image) {
        if(image.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

}
