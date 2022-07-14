package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.RoleService;
import com.hit.product.domains.dtos.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(UrlConstant.Role.DATA_ROLE)
    public ResponseEntity<?> getAllRole() {
        return VsResponseUtil.ok(roleService.getAllRole());
    }

    @GetMapping(UrlConstant.Role.DATA_ROLE_ID)
    public ResponseEntity<?> getRoleById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(roleService.findRoleById(id));
    }

    @PostMapping(UrlConstant.Role.DATA_ROLE_CREATE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) {
        return VsResponseUtil.ok(roleService.createRole(roleDto));
    }

    @DeleteMapping(UrlConstant.Role.DATA_ROLE_ID)
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(roleService.deleteRoleById(id));
    }
}
