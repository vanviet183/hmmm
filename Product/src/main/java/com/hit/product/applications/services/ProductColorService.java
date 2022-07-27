package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.ProductColorDto;
import com.hit.product.domains.entities.Product;
import com.hit.product.domains.entities.ProductColor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductColorService {

    List<ProductColor> getAll();

    ProductColor getProductColorById(Long id);

    ProductColor updateProductColor(Long id, ProductColorDto productColorDto);

    TrueFalseResponse deleteProductColor(Long id);

    Product createListProductColorForProduct(Long idProduct, List<ProductColorDto> productColorDTOs);
}
