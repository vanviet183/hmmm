package com.hit.product.domains.dtos;

import com.hit.product.applications.constants.Common;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String fullName;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String email;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String phone;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String address;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String note;
}
