package com.hit.product.adapter.web.base;

import com.hit.product.domains.dtos.pagination.PaginateDto;
import com.hit.product.domains.dtos.pagination.PaginationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class BasePagination<E, R extends JpaRepository<E, ?>> {

  private R repository;

  public BasePagination() {
  }

  public BasePagination(R repository) {
    this.repository = repository;
  }

  public PaginateDto<E> paginate(Integer page, Integer perPage) {
    if (page == null) {
      page = 0;
    }
    if (perPage == null) {
      perPage = 10;
    }
    Page<E> pageData = repository.findAll(PageRequest.of(page, perPage, Sort.by("createdDate").descending()));

    PaginationDto.Pagination pagination = new PaginationDto.Pagination(page, perPage, pageData.getTotalPages() - 1, pageData.getTotalElements());
    return new PaginateDto<>(pageData, pagination);
  }

  public PaginateDto<E> paginate(Integer page, Integer perPage, Page<E> pageData) {
    PaginationDto.Pagination pagination = new PaginationDto.Pagination(page, perPage, pageData.getTotalPages() - 1, pageData.getTotalElements());
    return new PaginateDto<>(pageData, pagination);
  }

}
