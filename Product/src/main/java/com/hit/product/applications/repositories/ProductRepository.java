package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySlug(String nameProduct);

    List<Product> findBySlugContaining(@Param("nameProduct") String nameProduct);

    List<Product> findByType(String type);

    List<Product> findByBrand(String brand);
}
