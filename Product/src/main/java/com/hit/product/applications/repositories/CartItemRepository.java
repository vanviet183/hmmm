package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByBill_Id(Long idBill);

    Optional<CartItem> findByUser_IdAndProduct_Id(Long idUser, Long idProduct);
}
