package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {

    Optional<ProductSize> findByValue(Integer value);
}
