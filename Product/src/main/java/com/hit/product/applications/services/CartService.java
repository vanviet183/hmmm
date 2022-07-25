package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.CartDto;
import com.hit.product.domains.entities.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<Cart> getCarts();

    Cart getCartById(Long id);

//    Cart createCart(CartDto CartDto);

//    Cart createCart(Long idBill, Long idProduct, CartDto CartDto);

    Cart createCart(Long idProduct, CartDto cartDto);

    Cart updateCart(Long id, CartDto cartDto);

    Cart pay(Long id, BillDto billDto);

    TrueFalseResponse deleteCart(Long id);
}
