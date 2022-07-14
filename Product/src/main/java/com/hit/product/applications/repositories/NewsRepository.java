package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.Banner;
import com.hit.product.domains.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
