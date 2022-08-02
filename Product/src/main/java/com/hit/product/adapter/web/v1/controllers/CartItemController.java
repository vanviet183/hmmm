package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.CartItemService;
import com.hit.product.domains.dtos.BillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @GetMapping(UrlConstant.CartItem.DATA_CART_ITEM)
    public ResponseEntity<?> getCarts() {
        return VsResponseUtil.ok(cartItemService.getCarts());
    }

    @GetMapping(UrlConstant.CartItem.DATA_CART_ITEM_ID)
    public ResponseEntity<?> getCartById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(cartItemService.getCartById(id));
    }

    @GetMapping(UrlConstant.CartItem.DATA_CART_ITEM_LIST_BY_ID)
    public ResponseEntity<?> getListCartItemById(@RequestParam("id") List<Long> listId) {
        return VsResponseUtil.ok(cartItemService.getListCartItemById(listId));
    }

    @PostMapping(UrlConstant.CartItem.DATA_CART_ITEM_ADD_PRODUCT)
    public ResponseEntity<?> addProductToCart(@PathVariable("idUser") Long idUser,
                                              @PathVariable("idProduct") Long idProduct) {
        return VsResponseUtil.ok(cartItemService.addProductToCart(idUser, idProduct));
    }

    @PostMapping(UrlConstant.CartItem.DATA_CART_ITEM_ID)
    public ResponseEntity<?> changeAmount(@PathVariable("id") Long id, @RequestParam("amount") Integer amount) {
        return VsResponseUtil.ok(cartItemService.changeAmount(id, amount));
    }

    @DeleteMapping(UrlConstant.CartItem.DATA_CART_ITEM_ID)
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(cartItemService.deleteCart(id));
    }

}
