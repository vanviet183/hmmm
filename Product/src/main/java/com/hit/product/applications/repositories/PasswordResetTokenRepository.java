package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    List<PasswordResetToken> findByStatus(Boolean status);

    PasswordResetToken findByToken(String token);
}
