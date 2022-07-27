package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.CartService;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(UrlConstant.Cart.DATA_CART)
    public ResponseEntity<?> getCarts() {
        return VsResponseUtil.ok(cartService.getCarts());
    }

    @GetMapping(UrlConstant.Cart.DATA_CART_ID)
    public ResponseEntity<?> getCartById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(cartService.getCartById(id));
    }

    @PostMapping(UrlConstant.Cart.DATA_CART_CREATE)
    public ResponseEntity<?> createCart(@PathVariable("idProduct") Long idProduct, @RequestBody CartDto cartDto) {
        return VsResponseUtil.ok(cartService.createCart(idProduct, cartDto));
    }

    @PatchMapping(UrlConstant.Cart.DATA_CART_ID)
    public ResponseEntity<?> updateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {
        return VsResponseUtil.ok(cartService.updateCart(id, cartDto));
    }

    @PostMapping("/{idCart}/pay")
    public ResponseEntity<?> pay(@PathVariable("idCart") Long idCart, @RequestBody BillDto billDto) {
        return VsResponseUtil.ok(cartService.pay(idCart, billDto));
    }

    @DeleteMapping(UrlConstant.Cart.DATA_CART_ID)
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(cartService.deleteCart(id));
    }

}
