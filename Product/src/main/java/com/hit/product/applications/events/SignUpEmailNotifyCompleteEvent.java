package com.hit.product.applications.events;

import com.hit.product.domains.entities.EmailNotification;
import com.hit.product.domains.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Setter
@Getter
public class SignUpEmailNotifyCompleteEvent extends ApplicationEvent {

    private EmailNotification emailNotification;
    private String applicationUrl;

    public SignUpEmailNotifyCompleteEvent(EmailNotification emailNotification, String applicationUrl) {
        super(emailNotification);
        this.emailNotification = emailNotification;
        this.applicationUrl = applicationUrl;
    }
}
