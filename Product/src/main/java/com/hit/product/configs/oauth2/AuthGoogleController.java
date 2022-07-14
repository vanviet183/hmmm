package com.hit.product.configs.oauth2;

import com.hit.product.adapter.web.base.VsResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/google")
public class AuthGoogleController {

  //   /oauth2/authorization/google

  private final GoogleService googleService;

  public AuthGoogleController(GoogleService googleService) {
    this.googleService = googleService;
  }

  @GetMapping("/save")
  public ResponseEntity<?> save(OAuth2AuthenticationToken token) {
    return VsResponseUtil.ok(googleService.saveUserWithFirstLogin(token));
  }

}
