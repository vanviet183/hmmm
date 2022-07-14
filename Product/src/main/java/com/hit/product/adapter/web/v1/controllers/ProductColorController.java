package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.ProductColorService;
import com.hit.product.domains.dtos.ProductColorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class ProductColorController {

    @Autowired
    ProductColorService productColorService;

    @GetMapping(UrlConstant.ProductColor.DATA_PRODUCT_COLOR)
    public ResponseEntity<?> getProductColors() {
        return VsResponseUtil.ok(productColorService.getAll());
    }

    @GetMapping(UrlConstant.ProductColor.DATA_PRODUCT_COLOR_ID)
    public ResponseEntity<?> getProductColor(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productColorService.getProductColorById(id));
    }

    @PostMapping(UrlConstant.ProductColor.DATA_PRODUCT_COLOR_CREATE)
    public ResponseEntity<?> createProductColor(@RequestBody List<ProductColorDto> productColorDTOs) {
        return VsResponseUtil.ok(productColorService.createListProductColor(productColorDTOs));
    }

    @PatchMapping(UrlConstant.ProductColor.DATA_PRODUCT_COLOR_ID)
    public ResponseEntity<?> updateProductColor(@PathVariable("id") Long id, @RequestBody ProductColorDto productColorDto) {
        return VsResponseUtil.ok(productColorService.updateProductColor(id, productColorDto));
    }

    @DeleteMapping(UrlConstant.ProductColor.DATA_PRODUCT_COLOR_ID)
    public ResponseEntity<?> deleteProductColor(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productColorService.deleteProductColor(id));
    }
}
