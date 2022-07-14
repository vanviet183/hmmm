package com.hit.product.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_notification_tokens")
public class EmailNotificationToken {

    // Expiration time 10 minutes
    private static final int EXPIRATION_TIME = 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    private Boolean status = Boolean.FALSE;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_email",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_EMAIL_VERIFY_TOKEN"))
    private EmailNotification emailNotification;

    public EmailNotificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    public EmailNotificationToken(EmailNotification emailNotification, String token) {
        super();
        this.emailNotification = emailNotification;
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.HOUR, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
