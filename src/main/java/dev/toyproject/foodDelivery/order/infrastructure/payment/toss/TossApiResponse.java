package dev.toyproject.foodDelivery.order.infrastructure.payment.toss;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class TossApiResponse {

    @Getter
    @Builder
    @ToString
    public static class response{
        private final String checkoutPage;
        private final String payToken;
        private final String code;
        private final String msg;
    }
}
