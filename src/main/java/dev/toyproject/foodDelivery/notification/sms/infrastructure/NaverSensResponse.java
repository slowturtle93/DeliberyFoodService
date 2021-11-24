package dev.toyproject.foodDelivery.notification.sms.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class NaverSensResponse {

    @Getter
    @Builder
    @ToString
    public static class response{
        private String requestId;
        private String requestTime;
        private String statusCode;
        private String statusName;
    }
}
