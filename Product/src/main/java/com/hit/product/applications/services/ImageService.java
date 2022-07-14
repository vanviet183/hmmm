package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Image;
import com.hit.product.domains.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {

    List<Image> getAll();

    Image getImageById(Long id);

    Image updateImage(Long id, MultipartFile multipartFile);

    TrueFalseResponse deleteImage(Long id);
}
