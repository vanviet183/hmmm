package com.hit.product.domains.dtos;

import com.hit.product.applications.commons.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private ERole name;

    private String description;
}
