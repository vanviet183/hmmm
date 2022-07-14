package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.ProductSizeService;
import com.hit.product.domains.dtos.ProductSizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class ProductSizeController {

    @Autowired
    ProductSizeService productSizeService;

    @GetMapping(UrlConstant.ProductSize.DATA_PRODUCT_SIZE)
    public ResponseEntity<?> getProductSizes() {
        return VsResponseUtil.ok(productSizeService.getAll());
    }

    @GetMapping(UrlConstant.ProductSize.DATA_PRODUCT_SIZE_ID)
    public ResponseEntity<?> getProductSize(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productSizeService.getProductSizeById(id));
    }

    @PostMapping(UrlConstant.ProductSize.DATA_PRODUCT_SIZE_CREATE)
    public ResponseEntity<?> createProductSize(@RequestBody List<ProductSizeDto> productSizeDTOs) {
        return VsResponseUtil.ok(productSizeService.createListProductSize(productSizeDTOs));
    }

    @PatchMapping("/{idProduct}/{id}")
    public ResponseEntity<?> updateProductC(@PathVariable("idProduct") Long idProduct, @PathVariable("id") Long id, @RequestBody ProductSizeDto productSizeDto) {
        return VsResponseUtil.ok(productSizeService.updateProductSize(idProduct, id, productSizeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productSizeService.deleteProductSize(id));
    }
}
