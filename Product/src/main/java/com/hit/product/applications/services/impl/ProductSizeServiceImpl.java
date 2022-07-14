package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.ProductSizeRepository;
import com.hit.product.applications.services.ProductSizeService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.ProductSizeDto;
import com.hit.product.domains.entities.ProductSize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductSize> getAll() {
        return productSizeRepository.findAll();
    }

    @Override
    public ProductSize getProductSizeById(Long id) {
        Optional<ProductSize> productSize = productSizeRepository.findById(id);
        checkProductSizeException(productSize);
        return productSize.get();
    }

    @Override
    @Transactional
    public List<ProductSize> createListProductSize(List<ProductSizeDto> productSizeDTOs) {
        List<ProductSize> productSizes = new ArrayList<>();
        productSizeDTOs.forEach(productSizeDto -> {
            productSizes.add(createOrUpdate(new ProductSize(), productSizeDto));
        });
        return productSizes;
    }

    @Override
    public ProductSize updateProductSize(Long idProduct, Long id, ProductSizeDto productSizeDto) {
        Optional<ProductSize> productSize = productSizeRepository.findById(id);
        checkProductSizeException(productSize);

        return createOrUpdate(productSize.get(), productSizeDto);
    }

    private ProductSize createOrUpdate(ProductSize productSize, ProductSizeDto productSizeDto) {
        modelMapper.map(productSizeDto, productSize);
        return productSizeRepository.save(productSize);
    }

    @Override
    public TrueFalseResponse deleteProductSize(Long id) {
        return null;
    }

    private void checkProductSizeException(Optional<ProductSize> productSize) {
        if(productSize.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
