package dev.toyproject.foodDelivery.notification.email.domain;

public interface MailService {
    public void sendMail(MailSendRequest mailRequest);
}
