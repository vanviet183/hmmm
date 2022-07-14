package com.hit.product.applications.services.impl;

import com.hit.product.applications.commons.ERole;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.applications.repositories.RoleRepository;
import com.hit.product.applications.services.RoleService;
import com.hit.product.domains.dtos.RoleDto;
import com.hit.product.domains.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Set<Role> getAllRole() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role findRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        checkRoleException(role);
        return role.get();
    }

    @Override
    public Role getRoleByName(ERole name) {
        Optional<Role> role = roleRepository.findByName(name);
        checkRoleException(role);
        return role.get();
    }

    @Override
    public Role createRole(RoleDto roleDto) {
        Optional<Role> role = roleRepository.findByName(roleDto.getName());
        if(role.isEmpty()) {
            return createOrUpdate(new Role(), roleDto);
        }
        throw new DuplicateFormatFlagsException("Duplicate!");
    }

    @Override
    public Role editRoleById(Long id, RoleDto roleDto) {
        Optional<Role> role = roleRepository.findById(id);
        checkRoleException(role);
        return createOrUpdate(role.get(), roleDto);
    }

    public Role createOrUpdate(Role role, RoleDto roleDto) {
            modelMapper.map(roleDto, role);
            return roleRepository.save(role);
    }

    @Override
    public String deleteRoleById(Long id) {
        roleRepository.deleteById(id);
        return "Delete Successfully";
    }

    private void checkRoleException(Optional<Role> role) {
        if (role.isEmpty()) {
            throw new NotFoundException("Not found!");
        }
    }
}
