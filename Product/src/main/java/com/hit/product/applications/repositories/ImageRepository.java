package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.Category;
import com.hit.product.domains.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProduct_Id(Long id);
}
