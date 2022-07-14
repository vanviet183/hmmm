package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.ProductRateService;
import com.hit.product.domains.dtos.ProductRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class ProductRateController {
    
    @Autowired
    ProductRateService productRateService;

    @GetMapping(UrlConstant.ProductRate.DATA_PRODUCT_RATE)
    public ResponseEntity<?> getProductRates() {
        return VsResponseUtil.ok(productRateService.getAll());
    }

    @GetMapping(UrlConstant.ProductRate.DATA_PRODUCT_RATE_ID)
    public ResponseEntity<?> getProductRate(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productRateService.getProductRateById(id));
    }

    @PostMapping(UrlConstant.ProductRate.DATA_PRODUCT_RATE_CREATE)
    public ResponseEntity<?> createProductRate(@PathVariable("idProduct") Long idProduct,
                                               @PathVariable("idUser") Long idUser,
                                               @RequestBody ProductRateDto productRateDto) {
        return VsResponseUtil.ok(productRateService.createProductRate(idProduct, idUser, productRateDto));
    }

    @PatchMapping(UrlConstant.ProductRate.DATA_PRODUCT_RATE_ID)
    public ResponseEntity<?> updateProductRate(@PathVariable("id") Long id, @RequestBody ProductRateDto productRateDto) {
        return VsResponseUtil.ok(productRateService.updateProductRate(id, productRateDto));
    }

    @DeleteMapping(UrlConstant.ProductRate.DATA_PRODUCT_RATE_ID)
    public ResponseEntity<?> deleteProductRate(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(productRateService.deleteProductRate(id));
    }
}
