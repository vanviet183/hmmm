package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.BillRepository;
import com.hit.product.applications.repositories.CartItemRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.CartItemService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.entities.Bill;
import com.hit.product.domains.entities.CartItem;
import com.hit.product.domains.entities.Product;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CartItem> getCarts() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartById(Long id) {
        Optional<Bill> bill = billRepository.findById(id);
        checkBillException(bill);
        return cartItemRepository.findByBill_Id(id);
    }

    @Override
    public CartItem addProductToCart(Long idUser, Long idProduct) {
        Optional<CartItem> cartItem = cartItemRepository.findByUser_IdAndProduct_Id(idUser, idProduct);
        if(cartItem.isPresent()) {
            int amountCurrent = cartItem.get().getAmount();
            cartItem.get().setAmount(amountCurrent + 1);
        } else {
            Optional<Product> product = productRepository.findById(idProduct);
            checkProductException(product);

            Optional<User> user = userRepository.findById(idUser);
            checkUserException(user);

            CartItem cartItemNew = new CartItem();
            cartItemNew.setUser(user.get());
            cartItemNew.setProduct(product.get());
            return cartItemRepository.save(cartItemNew);
        }

        return cartItemRepository.save(cartItem.get());
    }

    @Override
    public CartItem changeAmount(Long id, int amount) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        checkCartItemException(cartItem);

        int amountCurrent = cartItem.get().getAmount();
        if(amountCurrent == 0 && amount == -1) {
            return cartItem.get();
        }
        cartItem.get().setAmount(amountCurrent + amount);

        return cartItemRepository.save(cartItem.get());
    }

    @Override
    @Transactional
    public List<CartItem> getListCartItemById(List<Long> listId) {
        List<CartItem> cartItems = new ArrayList<>();
        listId.forEach(id -> {
            cartItems.add(getCartById(id));
        });
        return cartItems;
    }

    @Override
    public TrueFalseResponse deleteCart(Long idBill) {
        Optional<Bill> bill = billRepository.findById(idBill);
        checkBillException(bill);
        billRepository.deleteById(idBill);
        return new TrueFalseResponse(true);
    }

    @Override
    public CartItem pay(Long id, BillDto billDto) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        checkCartItemException(cartItem);

        Bill bill = modelMapper.map(billDto, Bill.class);
        billRepository.save(bill);
        cartItem.get().setBill(bill);
        return cartItemRepository.save(cartItem.get());
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

    private void checkCartItemException(Optional<CartItem> Cart) {
        if(Cart.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
