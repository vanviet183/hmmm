package com.hit.product.applications.listens;

import com.hit.product.applications.events.RegistrationCompleteEvent;
import com.hit.product.applications.services.EmailSenderService;
import com.hit.product.applications.services.VerificationTokenService;
import com.hit.product.domains.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create the Verification Token for the User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        verificationTokenService.saveVerificationTokenForUser(token, user);

        // Send email to user
        log.info(event.getApplicationUrl());
        String url = event.getApplicationUrl()
                + "/verifyRegistration?token="
                + token;
        emailSenderService.sendSimpleEmail(user.getEmail(), url, "Verify Register VitApp Web");

        log.info("Click the link to verify your account: {}", url);
    }
}
