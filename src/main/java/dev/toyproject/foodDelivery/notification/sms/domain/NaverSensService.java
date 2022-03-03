package dev.toyproject.foodDelivery.notification.sms.domain;

import dev.toyproject.foodDelivery.notification.sms.infrastructure.NaverSensRequest;

public interface NaverSensService {
    public void sendNaverSens(NaverSensRequest.makeMessageInfo makeMessageInfo);
}
