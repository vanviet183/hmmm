package com.hit.product.applications.services.impl;

import com.hit.product.applications.repositories.PasswordResetTokenRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.PasswordResetTokenService;
import com.hit.product.domains.dtos.PasswordDto;
import com.hit.product.domains.entities.PasswordResetToken;
import com.hit.product.domains.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken == null) {
            return "invalid";
        }

        User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    @Override
    public void backUpDBPasswordResetToken() {
        List<PasswordResetToken> passwordResetTokens = passwordResetTokenRepository.findAll();
        Calendar calendar = Calendar.getInstance();

        passwordResetTokens.forEach(item -> {
            if (item.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
                    passwordResetTokenRepository.delete(item);
            }
        });
    }

    @Override
    public String resetPassword(PasswordDto passwordDto, HttpServletRequest request) {
        User user = userRepository.findByUsernameAndStatus(passwordDto.getUsername(), true);
        String url = "";

        if(user != null) {
            String token = UUID.randomUUID().toString();
            createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }
        return url;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl
                + "/savePassword?token="
                + token;
        emailSenderService.sendSimpleEmail(user.getEmail(), "Click the link to Reset your Password: " + url, "Reset Password VitApp Web");
        return url;
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        passwordResetTokenRepository.save(new PasswordResetToken(user, token));
        System.out.println("Luu thanh cong");
    }

    @Override
    public Boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public String applicationUrl(HttpServletRequest request) {
        System.out.println("request = " + request);
        System.out.println(request.getServerName());
        System.out.println(request.getServerPort());
        System.out.println(request.getContextPath());
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
