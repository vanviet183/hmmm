package com.hit.product.adapter.web.v1.transfer.responses;

import com.hit.product.domains.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
//    private Long userId;
    private String username;

    private List<Role> roleList;
}
