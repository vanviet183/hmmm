package com.hit.product.configs.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    @Autowired
//    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getEmail();
//        User user = userService.findUserByEmail(email);

        System.out.println(email);
//        System.out.println(user.getFirstName() + user.getLastName());

//        if(user == null) {
//            userService.createNewUserAfterOAuthLoginSuccess(email, oAuth2User.getName(), AuthenticationProvider.GOOGLE);
//        } else {
//            userService.updateUserAfterOAuthLoginSuccess(user, oAuth2User.getName(), AuthenticationProvider.GOOGLE);
//
//        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
