package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.events.SignUpEmailNotifyCompleteEvent;
import com.hit.product.applications.services.EmailNotificationService;
import com.hit.product.domains.dtos.EmailNotificationDto;
import com.hit.product.domains.entities.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RestApiV1
public class EmailNotificationController {

    @Autowired
    EmailNotificationService emailNotificationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping(UrlConstant.EmailNotification.DATA_EMAIL_NOTIFICATION_SIGN_UP)
    public ResponseEntity<?> signUpEmailNotification(@RequestBody EmailNotificationDto emailNotificationDto, final HttpServletRequest request) {
        EmailNotification emailNotification = emailNotificationService.signUpEmailNotification(emailNotificationDto);
        publisher.publishEvent(new SignUpEmailNotifyCompleteEvent(
                emailNotification,
                applicationUrl(request)
        ));

        return VsResponseUtil.ok(emailNotification);
    }

    @PostMapping(UrlConstant.EmailNotification.DATA_EMAIL_NOTIFICATION_CANCEL)
    public ResponseEntity<?> cancelEmailNotification(@RequestParam("email") String email) {
        return VsResponseUtil.ok(emailNotificationService.cancelEmailNotification(email));
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
