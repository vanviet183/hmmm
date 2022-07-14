package com.hit.product.applications.services.impl;

import com.github.slugify.Slugify;
import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.ProductColorRepository;
import com.hit.product.applications.services.ProductColorService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.ProductColorDto;
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
    public List<ProductColor> createListProductColor(List<ProductColorDto> productColorDTOs) {
        List<ProductColor> productColors = new ArrayList<>();
        productColorDTOs.forEach(productColorDto -> {
            productColors.add(createOrUpdate(new ProductColor(), productColorDto));
        });
        return productColors;
    }

    @Override
    public ProductColor updateProductColor(Long id, ProductColorDto productColorDto) {
        Optional<ProductColor> productColor = productColorRepository.findById(id);
        checkProductColorException(productColor);

        return createOrUpdate(productColor.get(), productColorDto);
    }


    private ProductColor createOrUpdate(ProductColor productColor, ProductColorDto productColorDto) {
        modelMapper.map(productColorDto, productColor);

        Slugify slug = new Slugify();
        String result = slug.slugify(productColorDto.getColor());
        productColor.setSlug(result);

        return productColorRepository.save(productColor);
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

}
