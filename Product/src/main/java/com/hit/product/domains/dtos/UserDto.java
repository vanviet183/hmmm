package com.hit.product.domains.dtos;

import com.hit.product.applications.constants.Common;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String firstName;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String lastName;

    @NotBlank()
    @Length(max = Common.STRING_LENGTH_LIMIT)
    @Size(min = 6, max = 20, message = "Too long")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&_]{8,255}$", message = "is invalid")
    private String password;

    @NotBlank
    @Email
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String email;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String phone;

    @NotBlank
    @Length(max = Common.STRING_LENGTH_LIMIT)
    private String gender;

}
