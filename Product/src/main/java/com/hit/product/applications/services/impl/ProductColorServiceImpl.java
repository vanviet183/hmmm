package com.hit.product.applications.services.impl;

import com.github.slugify.Slugify;
import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.ProductColorRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.services.ProductColorService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.ProductColorDto;
import com.hit.product.domains.entities.Product;
import com.hit.product.domains.entities.ProductColor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductColor> getAll() {
        return productColorRepository.findAll();
    }

    @Override
    public ProductColor getProductColorById(Long id) {
        Optional<ProductColor> productColor = productColorRepository.findById(id);
        checkProductColorException(productColor);
        return productColor.get();
    }

    @Override
    @Transactional
    public Product createListProductColorForProduct(Long idProduct, List<ProductColorDto> productColorDTOs) {
        Optional<Product> product = productRepository.findById(idProduct);
        checkProductException(product);
        Slugify slug = new Slugify();

        productColorDTOs.forEach(productColorDto -> {
            ProductColor productColor = modelMapper.map(productColorDto, ProductColor.class);
            productColor.setSlug(slug.slugify(productColorDto.getColor()));
            productColor.setProduct(product.get());
            productColorRepository.save(productColor);
        });
        return product.get();
    }


    @Override
    public ProductColor updateProductColor(Long id, ProductColorDto productColorDto) {
        Optional<ProductColor> productColor = productColorRepository.findById(id);
        checkProductColorException(productColor);

        modelMapper.map(productColorDto, productColor.get());

        Slugify slug = new Slugify();
        String result = slug.slugify(productColorDto.getColor());
        productColor.get().setSlug(result);

        return productColorRepository.save(productColor.get());
    }

    @Override
    public TrueFalseResponse deleteProductColor(Long id) {
        Optional<ProductColor> productColor = productColorRepository.findById(id);
        checkProductColorException(productColor);
        productColorRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }


    private void checkProductColorException(Optional<ProductColor> productColor) {
        if(productColor.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductException(Optional<Product> product) {
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

}
