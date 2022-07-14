package com.hit.product.applications.services.impl;

import com.hit.product.applications.repositories.EmailNotificationRepository;
import com.hit.product.applications.repositories.EmailNotificationTokenRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.repositories.VerificationTokenRepository;
import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domains.entities.EmailNotification;
import com.hit.product.domains.entities.EmailNotificationToken;
import com.hit.product.domains.entities.User;
import com.hit.product.domains.entities.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    EmailNotificationTokenRepository emailNotificationTokenRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    EmailNotificationRepository emailNotificationRepository;

    @Override
    public String validateVerificationToken(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken.isEmpty()) {
            return "invalid";
        }

        User user = verificationToken.get().getUser();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.get().getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken.get());
            return "expired";
        }

        // Update status user and verification token
        user.setStatus(true);
        userRepository.save(user);
        verificationToken.get().setStatus(true);
        verificationTokenRepository.save(verificationToken.get());

        return "valid";
    }

    @Override
    public String validateVerificationEmailNotificationToken(String token) {
        Optional<EmailNotificationToken> emailNotificationToken = emailNotificationTokenRepository.findByToken(token);
        if(emailNotificationToken.isEmpty()) {
            return "invalid";
        }

        EmailNotification emailNotification = emailNotificationToken.get().getEmailNotification();
        Calendar calendar = Calendar.getInstance();

        if((emailNotificationToken.get().getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            emailNotificationTokenRepository.delete(emailNotificationToken.get());
            return "expired";
        }

        // Update status user and verification token
        emailNotification.setStatus(true);
        emailNotificationRepository.save(emailNotification);
        emailNotificationToken.get().setStatus(true);
        emailNotificationTokenRepository.save(emailNotificationToken.get());

        return "valid";
    }

    @Override
    public void backUpDBUser() {
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByStatus(false);
        Calendar calendar = Calendar.getInstance();

        verificationTokens.forEach(item -> {
            if (item.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
                if (item.getUser().getStatus() == false) {
                    verificationTokenRepository.delete(item);
                    userRepository.deleteById(item.getUser().getId());
                    System.out.println("Delete thanh cong");
                }
            }
        });
    }

    @Override
    public void backUpDBEmailNotification() {
        List<EmailNotificationToken> emailNotificationTokens = emailNotificationTokenRepository.findByStatus(false);
        Calendar calendar = Calendar.getInstance();

        emailNotificationTokens.forEach(item -> {
            if (item.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
                if (item.getEmailNotification().getStatus() == false) {
                    emailNotificationTokenRepository.delete(item);
                    emailNotificationRepository.deleteById(item.getEmailNotification().getId());
                    System.out.println("Delete thanh cong");
                }
            }
        });
    }

    @Override
    public void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl
                + "/verifyRegistration?token="
                + verificationToken.getToken();
        emailSenderService.sendSimpleEmail(user.getEmail(), url, "Verify Token VitApp Web");
    }



    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.get().setToken(UUID.randomUUID().toString());
        return verificationTokenRepository.save(verificationToken.get());
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void saveVerificationTokenForEmailNotification(String token, EmailNotification emailNotification) {
        EmailNotificationToken emailNotificationToken = new EmailNotificationToken(emailNotification, token);
        emailNotificationTokenRepository.save(emailNotificationToken);
    }

}
