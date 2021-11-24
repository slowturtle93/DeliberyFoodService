package dev.toyproject.foodDelivery.notification.sms.domain;

public interface NaverSensKeyFactory {
    public String makeSignature(String serviceId, String timeStamp, String accessKey, String secretKey);
}
