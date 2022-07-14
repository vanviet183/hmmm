package com.hit.product.configs.oauth2;

import com.hit.product.applications.commons.AuthenticationProvider;
import com.hit.product.applications.commons.ERole;
import com.hit.product.applications.repositories.RoleRepository;
import com.hit.product.applications.services.MyUserDetailsService;
import com.hit.product.applications.services.RoleService;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.utils.JwtUtil;
import com.hit.product.domains.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleService {

  @Autowired
  RoleService roleService;

  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Autowired
  JwtUtil jwtUtil;

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  String saveUserWithFirstLogin(OAuth2AuthenticationToken token) {
    String email = token.getPrincipal().getAttributes().get("email").toString();
    String username = email.substring(0, email.indexOf('@'));

    User user = userService.findUserByUsername(username);

    if (user != null) {
      return jwtUtil.generateToken(myUserDetailsService.loadUserByUsername(username));
    }

    user = new User();
    user.setAuthProvider(AuthenticationProvider.GOOGLE);
    user.setPhone(token.getPrincipal().getAttributes().get("sub").toString().substring(0, 10));

    user.setFirstName(token.getPrincipal().getAttributes().get("name").toString());

    user.setUsername(username);
    user.setPassword(passwordEncoder().encode(username));
    user.setAvatar((String) token.getPrincipal().getAttributes().get("picture"));
    user.setEmail(email);

    user.setRoles(List.of(roleRepository.findByName(ERole.ROLE_USER).get()));

    User newUser = userService.save(user);

    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(newUser.getUsername());

    return jwtUtil.generateToken(userDetails);
  }

}
