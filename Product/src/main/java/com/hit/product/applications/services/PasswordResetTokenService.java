package com.hit.product.applications.services;

import com.hit.product.domains.dtos.PasswordDto;
import com.hit.product.domains.entities.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public interface PasswordResetTokenService {

    String validatePasswordResetToken(String token);

    void backUpDBPasswordResetToken();

    String resetPassword(PasswordDto passwordDto, HttpServletRequest request);

    void changePassword(User user, String newPassword);

    void createPasswordResetTokenForUser(User user, String token);

    Boolean checkIfValidOldPassword(User user, String oldPassword);

    Optional<User> getUserByPasswordResetToken(String token);

}
