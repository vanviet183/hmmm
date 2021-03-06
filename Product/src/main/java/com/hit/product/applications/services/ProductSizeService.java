package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.ProductSizeDto;
import com.hit.product.domains.entities.Product;
import com.hit.product.domains.entities.ProductSize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductSizeService {

    List<ProductSize> getAll();

    ProductSize getProductSizeById(Long id);

    ProductSize updateProductSize(Long id, ProductSizeDto productSizeDto);

    TrueFalseResponse deleteProductSize(Long id);

    Product createListProductSizeForProduct(Long idProduct, List<ProductSizeDto> productSizeDTOs);
}
