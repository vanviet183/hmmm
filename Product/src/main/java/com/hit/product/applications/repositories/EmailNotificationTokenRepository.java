package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.EmailNotification;
import com.hit.product.domains.entities.EmailNotificationToken;
import com.hit.product.domains.entities.User;
import com.hit.product.domains.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailNotificationTokenRepository extends JpaRepository<EmailNotificationToken, Long> {

    Optional<EmailNotificationToken> findByToken(String token);

    List<EmailNotificationToken> findByStatus(Boolean status);

//    Optional<EmailNotification> findVerificationTokensByUser(EmailNotification emailNotification);
}
