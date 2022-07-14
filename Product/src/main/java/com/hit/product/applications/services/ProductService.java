package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.Image;
import com.hit.product.domains.entities.Product;
import com.hit.product.domains.entities.ProductColor;
import com.hit.product.domains.entities.ProductSize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllByPage(Integer page);

    Product getProductById(Long id);

    List<Image> getImageByProductId(Long id);

    Product createProduct(Long categoryId,
                          ProductDto productDto,
                          List<ProductColor> productColorDTOs,
                          List<ProductSize> productSizeDTOs,
                          List<MultipartFile> multipartFiles);

    Product updateProduct(Long id, ProductDto productDto, List<MultipartFile> multipartFiles);

    TrueFalseResponse deleteProduct(Long id);

    List<Product> searchProducts(String nameProduct);

    List<Product> getProductsSort(String type);

    List<Product> getProductsNewest(Integer page);

    List<Product> getProductsBestSeller(Integer page);

    List<Product> getProductsByFilter(List<String> types, List<Integer> sizes, List<String> colors, List<String> brands);
}
