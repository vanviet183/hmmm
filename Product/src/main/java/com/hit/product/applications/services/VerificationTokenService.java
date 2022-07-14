package com.hit.product.applications.services;

import com.hit.product.domains.entities.EmailNotification;
import com.hit.product.domains.entities.User;
import com.hit.product.domains.entities.VerificationToken;
import org.springframework.stereotype.Service;


@Service
public interface VerificationTokenService {

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken);

    void backUpDBUser();

    void backUpDBEmailNotification();

    void saveVerificationTokenForUser(String token, User user);

    void saveVerificationTokenForEmailNotification(String token, EmailNotification emailNotification);

    String validateVerificationEmailNotificationToken(String token);

}
