package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.ProductColor;
import com.hit.product.domains.entities.ProductRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRateRepository extends JpaRepository<ProductRate, Long> {

}
