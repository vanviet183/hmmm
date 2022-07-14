package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.parameters.auth.AuthenticationRequest;
import com.hit.product.adapter.web.v1.transfer.responses.AuthenticationResponse;
import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.configs.exceptions.VsException;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.User;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;

@Service
public interface AuthService {

  AuthenticationResponse login(AuthenticationRequest request) throws VsException;

  User registerUser(UserDto userDto);

//  TrueFalseResponse validateToken(AuthenticationResponse authenticationResponse);

//  TrueFalseResponse logoutHandler(Long id);

//  AuthenticationResponse signup(UserDto userDto) throws InvalidObjectException;

}