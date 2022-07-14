package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.ProductRateDto;
import com.hit.product.domains.entities.ProductRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRateService {

    List<ProductRate> getAll();

    ProductRate getProductRateById(Long id);

    ProductRate createProductRate(Long idProduct, Long idUser, ProductRateDto ProductRateDto);

    ProductRate updateProductRate(Long id, ProductRateDto ProductRateDto);

    TrueFalseResponse deleteProductRate(Long id);
}
