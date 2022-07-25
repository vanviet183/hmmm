package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.BillRepository;
import com.hit.product.applications.repositories.CartRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.services.CartService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.CartDto;
import com.hit.product.domains.entities.Bill;
import com.hit.product.domains.entities.Cart;
import com.hit.product.domains.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        Optional<Bill> bill = billRepository.findById(id);
        checkBillException(bill);
        return cartRepository.findByBill_Id(id);
    }

//    @Override
//    public Cart createCart(CartDto CartDto) {
//        return createOrUpdate(new Cart(), CartDto);
//    }

//    @Override
//    public Cart createCart(Long idBill, Long idProduct, CartDto CartDto) {
//        return createOrUpdate(idBill, idProduct, new Cart(), CartDto);
//    }

    @Override
    public Cart createCart(Long idProduct, CartDto cartDto) {
        return createOrUpdate(idProduct, new Cart(), cartDto);
    }

    private Cart createOrUpdate(Long idProduct, Cart cart, CartDto cartDto) {
        Optional<Product> product = productRepository.findById(idProduct);
        checkProductException(product);
        modelMapper.map(cartDto, cart);
        cart.setProduct(product.get());

        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long id, CartDto cartDto) {
        Optional<Cart> Cart = cartRepository.findById(id);
        checkCartException(Cart);
        modelMapper.map(cartDto, Cart.get());
        return cartRepository.save(Cart.get());
    }

    @Override
    public Cart pay(Long id, BillDto billDto) {
        Optional<Cart> Cart = cartRepository.findById(id);
        checkCartException(Cart);

        Bill bill = modelMapper.map(billDto, Bill.class);
        billRepository.save(bill);
        Cart.get().setBill(bill);
        return cartRepository.save(Cart.get());
    }

//    private Cart createOrUpdate(Long idBill, Long idProduct, Cart Cart, CartDto CartDto) {
//        Optional<Bill> bill = billRepository.findById(idBill);
//        checkBillException(bill);
//
//        Optional<Product> product = productRepository.findById(idProduct);
//        checkProductException(product);
//        modelMapper.map(CartDto, Cart);
//        Cart.setBill(bill.get());
//        Cart.setProduct(product.get());
//
//        return CartRepository.save(Cart);
//    }


    @Override
    public TrueFalseResponse deleteCart(Long idBill) {
        Optional<Bill> bill = billRepository.findById(idBill);
        checkBillException(bill);
        billRepository.deleteById(idBill);
        return new TrueFalseResponse(true);
    }

    private void checkBillException(Optional<Bill> bill) {
        if(bill.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductException(Optional<Product> product) {
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkCartException(Optional<Cart> Cart) {
        if(Cart.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
