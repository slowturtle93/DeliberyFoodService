package dev.toyproject.foodDelivery.notification.email.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MailSendRequest {
    protected String address;
    protected String title;
    protected String message;
}
