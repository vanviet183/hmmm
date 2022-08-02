package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.entities.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {

    List<CartItem> getCarts();

    CartItem getCartById(Long id);

    CartItem pay(Long id, BillDto billDto);

    TrueFalseResponse deleteCart(Long id);

    CartItem addProductToCart(Long idUser, Long idProduct);

    CartItem changeAmount(Long id, int amount);

    List<CartItem> getListCartItemById(List<Long> listId);
}
