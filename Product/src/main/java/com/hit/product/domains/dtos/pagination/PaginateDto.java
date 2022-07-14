package com.hit.product.domains.dtos.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginateDto<T> {
    private Page<T> pageData;
    private PaginationDto.Pagination pagination;
}
