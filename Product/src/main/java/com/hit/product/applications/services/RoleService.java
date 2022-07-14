package com.hit.product.applications.services;

import com.hit.product.applications.commons.ERole;
import com.hit.product.domains.dtos.RoleDto;
import com.hit.product.domains.entities.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Set<Role> getAllRole();

    Role findRoleById(Long id);

    Role getRoleByName(ERole name);

    Role createRole(RoleDto roleDto);

    Role editRoleById(Long id, RoleDto roleDto);

    String deleteRoleById(Long id);

//    Role save(Role role);
}
