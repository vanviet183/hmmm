package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.NotificationRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.NotificationService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.NotificationDto;
import com.hit.product.domains.entities.Notification;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        checkNotificationException(notification);
        return notification.get();
    }

    // Notification of system
    @Override
    public Notification createNotification(NotificationDto notificationDto) {
        return createOrUpdate(new Notification(), notificationDto);
    }

    // Notification of user
    @Override
    public Notification createNotificationForUser(Long idUser, NotificationDto notificationDto) {
        Optional<User> user = userRepository.findById(idUser);
        checkUserException(user);
        Notification notification = createOrUpdate(new Notification(), notificationDto);
        notification.setUsers(List.of(user.get()));
        return notification;
    }

    @Override
    public Notification updateNotification(Long id, NotificationDto notificationDto) {
        Optional<Notification> notification = notificationRepository.findById(id);
        checkNotificationException(notification);
        return createOrUpdate(notification.get(), notificationDto);
    }

    private Notification createOrUpdate(Notification notification, NotificationDto notificationDto) {
        modelMapper.map(notificationDto, notification);
        return notificationRepository.save(notification);
    }

    @Override
    public TrueFalseResponse deleteNotification(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        checkNotificationException(notification);
        notificationRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkNotificationException(Optional<Notification> notification) {
        if(notification.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
