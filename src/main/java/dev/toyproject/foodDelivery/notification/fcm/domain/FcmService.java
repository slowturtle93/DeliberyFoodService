package dev.toyproject.foodDelivery.notification.fcm.domain;

public interface FcmService {

    public void sendFcm(FcmNotificationRequest fcmNotificationRequest);
}
