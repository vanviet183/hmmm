package com.hit.product.applications.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendSimpleEmail(String toEmail, String body, String subject);
}
