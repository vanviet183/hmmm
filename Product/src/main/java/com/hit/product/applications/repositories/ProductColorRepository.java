package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

    List<ProductColor> findBySlug(String slug);
}
