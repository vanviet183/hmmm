package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByBill_Id(Long idBill);
}
