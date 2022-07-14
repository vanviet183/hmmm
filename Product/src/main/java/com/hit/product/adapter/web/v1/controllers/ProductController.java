package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.ProductService;
import com.hit.product.domains.dtos.ProductDto;
import com.hit.product.domains.entities.ProductColor;
import com.hit.product.domains.entities.ProductSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestApiV1
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(UrlConstant.Product.DATA_PRODUCT)
    public ResponseEntity<?> getProducts(@RequestParam("page") Integer page) {
        return ResponseEntity.ok().body(productService.getAllByPage(page));
    }

    @GetMapping(UrlConstant.Product.DATA_PRODUCT_NEWS)
    public ResponseEntity<?> getProductsNewest(@RequestParam("page") Integer page) {
        return ResponseEntity.ok().body(productService.getProductsNewest(page));
    }

    @GetMapping(UrlConstant.Product.DATA_PRODUCT_SELL_BEST)
    public ResponseEntity<?> getProductsBestSeller(@RequestParam("page") Integer page) {
        return ResponseEntity.ok().body(productService.getProductsBestSeller(page));
    }

    @GetMapping(UrlConstant.Product.DATA_PRODUCT_SORT)
    public ResponseEntity<?> getProductsSort(@RequestParam("by") String type) {
        return ResponseEntity.ok().body(productService.getProductsSort(type));
    }

    @GetMapping(UrlConstant.Product.DATA_PRODUCT_FILTER)
    public ResponseEntity<?> getProductsByFilter(@RequestParam("type") List<String> types,
                                                 @RequestParam("size") List<Integer> sizes,
                                                 @RequestParam("color") List<String> colors,
                                                 @RequestParam("brand") List<String> brands) {
        return ResponseEntity.ok().body(productService.getProductsByFilter(types, sizes, colors, brands));
    }

    @GetMapping(UrlConstant.Product.DATA_PRODUCT_ID)
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping(UrlConstant.Product.DATA_PRODUCT_CREATE)
    public ResponseEntity<?> createProduct(@PathVariable("idCategory") Long idCategory,
                                           @RequestBody ProductDto productDto,
                                           @RequestBody List<ProductColor> productColors,
                                           @RequestBody List<ProductSize> productSizes,
                                           @RequestParam("img") List<MultipartFile> multipartFiles) {
        return VsResponseUtil.ok(productService.createProduct(idCategory, productDto, productColors, productSizes, multipartFiles));
    }

    @PostMapping(UrlConstant.Product.DATA_PRODUCT_SEARCH)
    public ResponseEntity<?> searchProducts(@RequestParam("key") String key) {
        return VsResponseUtil.ok(productService.searchProducts(key));
    }

    @PatchMapping(UrlConstant.Product.DATA_PRODUCT_ID)
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody ProductDto productDto,
                                           @RequestParam("img") List<MultipartFile> multipartFiles) {
        return VsResponseUtil.ok(productService.updateProduct(id, productDto, multipartFiles));
    }

    @DeleteMapping(UrlConstant.Product.DATA_PRODUCT_ID)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productService.deleteProduct(id));
    }

}
