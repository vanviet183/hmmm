package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.Banner;
import com.hit.product.domains.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findBySlugContaining(@Param("q")String title);
}
