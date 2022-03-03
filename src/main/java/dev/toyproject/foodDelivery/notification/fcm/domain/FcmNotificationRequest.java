package dev.toyproject.foodDelivery.notification.fcm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FcmNotificationRequest {

    private String title;
    private String message;
    private String token;

    @Builder
    public FcmNotificationRequest(String title, String message, String token) {
        this.title = title;
        this.message = message;
        this.token = token;
    }
}
