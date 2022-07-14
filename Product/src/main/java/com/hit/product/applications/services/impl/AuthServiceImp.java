package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.parameters.auth.AuthenticationRequest;
import com.hit.product.adapter.web.v1.transfer.responses.AuthenticationResponse;
import com.hit.product.applications.commons.AuthenticationProvider;
import com.hit.product.applications.commons.ERole;
import com.hit.product.applications.repositories.RoleRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.AuthService;
import com.hit.product.applications.services.MyUserDetailsService;
import com.hit.product.applications.utils.JwtUtil;
import com.hit.product.configs.exceptions.VsException;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImp implements AuthService {

  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Autowired
  ModelMapper modelMapper;


  @Override
  public AuthenticationResponse login(AuthenticationRequest request) throws VsException {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          request.getUsername(), request.getPassword()
      ));
    } catch (BadCredentialsException e) {
      throw new VsException("Incorrect username or password");
    }

    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    User user = userRepository.findByUsernameAndStatus(request.getUsername(), true);

    return new AuthenticationResponse(jwt, user.getUsername(), user.getRoles());
  }

  @Override
  public User registerUser(UserDto userDto) {
    User user = modelMapper.map(userDto, User.class);
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setRoles(List.of(roleRepository.findByName(ERole.ROLE_USER).get()));
    user.setAuthProvider(AuthenticationProvider.LOCAL);
    return userRepository.save(user);
  }

//  @Override
//  public AuthenticationResponse signup(UserDto userDto) {
//    User oldUser = userService.findUserByUsername(userDto.getUsername());
//    if (oldUser != null) {
//      throw new VsException("Username has already exists");
//    }
//    User user = modelMapper.map(userDto, User.class);
//    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//    user.setRoles(List.of(roleRepository.findByName(ERole.ROLE_USER).get()));
//
//    user.setAuthProvider(AuthenticationProvider.LOCAL);
//    User newUser = userService.save(user);
////
//    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(newUser.getUsername());
//    final String jwt = jwtUtil.generateToken(userDetails);
////
//    return new AuthenticationResponse(jwt, newUser.getUsername(), user.getRoles());/
//  }


//  @Override
//  public TrueFalseResponse validateToken(AuthenticationResponse authenticationResponse) {
//    try {
//      String jwt = authenticationResponse.getJwt();
//      String username = jwtUtil.getUsernameFromJwtToken(jwt);
//      UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
//
//      return new TrueFalseResponse(jwtUtil.validateJwtToken(jwt));
//
//    } catch (Exception e) {
//      return new TrueFalseResponse(false);
//    }
//  }
}