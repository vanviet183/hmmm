package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.ProductRateRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.ProductRateService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.ProductRateDto;
import com.hit.product.domains.entities.Product;
import com.hit.product.domains.entities.ProductRate;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRateServiceImpl implements ProductRateService {

    @Autowired
    ProductRateRepository productRateRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductRate> getAll() {
        return productRateRepository.findAll();
    }

    @Override
    public ProductRate getProductRateById(Long id) {
        Optional<ProductRate> productRate = productRateRepository.findById(id);
        checkProductRateException(productRate);
        return productRate.get();
    }

    @Override
    public ProductRate createProductRate(Long idProduct, Long idUser, ProductRateDto productRateDto) {
        Optional<Product> product = productRepository.findById(idProduct);
        checkProductException(product);

        Optional<User> user = userRepository.findById(idUser);
        checkUserException(user);

        ProductRate productRate = modelMapper.map(productRateDto, ProductRate.class);
        productRate.setProduct(product.get());
        productRate.setUser(user.get());

        return productRateRepository.save(productRate);
    }

    @Override
    public ProductRate updateProductRate(Long id, ProductRateDto productRateDto) {
        Optional<ProductRate> productRate = productRateRepository.findById(id);
        checkProductRateException(productRate);

        modelMapper.map(productRateDto, productRate.get());
        return productRateRepository.save(productRate.get());
    }

    @Override
    public TrueFalseResponse deleteProductRate(Long id) {
        Optional<ProductRate> productRate = productRateRepository.findById(id);
        checkProductRateException(productRate);
        productRateRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkProductException(Optional<Product> product) {
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductRateException(Optional<ProductRate> productRate) {
        if(productRate.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
