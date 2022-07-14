package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.adapter.web.v1.transfer.parameters.auth.AuthenticationRequest;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.events.RegistrationCompleteEvent;
import com.hit.product.applications.services.AuthService;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping (UrlConstant.Auth.LOGIN)
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return VsResponseUtil.ok(authService.login(authenticationRequest));
    }

    @PostMapping (UrlConstant.Auth.SIGNUP)
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto, final HttpServletRequest request) {

        User user = authService.registerUser(userDto);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return ResponseEntity.ok().body(user);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
