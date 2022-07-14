package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.EmailNotificationRepository;
import com.hit.product.applications.services.EmailNotificationService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.EmailNotificationDto;
import com.hit.product.domains.entities.EmailNotification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailNotificationRepository emailNotificationRepository;

    @Override
    public EmailNotification signUpEmailNotification(EmailNotificationDto emailNotificationDto) {
        EmailNotification emailNotification = modelMapper.map(emailNotificationDto, EmailNotification.class);
        return emailNotificationRepository.save(emailNotification);
    }

    @Override
    public TrueFalseResponse cancelEmailNotification(String email) {
        Optional<EmailNotification> emailNotification = emailNotificationRepository.findByEmail(email);
        checkEmailNotification(emailNotification);
        emailNotificationRepository.delete(emailNotification.get());
        return new TrueFalseResponse(true);
    }

    private void checkEmailNotification(Optional<EmailNotification> emailNotification) {
        if(emailNotification.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
