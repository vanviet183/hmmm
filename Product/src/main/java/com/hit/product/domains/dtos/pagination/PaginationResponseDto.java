package com.hit.product.domains.dtos.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationResponseDto<T> {
    private Integer status;
    private String message;
    private PaginationDto<T> result;
}
