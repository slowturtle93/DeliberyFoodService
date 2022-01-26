package dev.toyproject.foodDelivery.order.domain.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PaymentCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterPayment{
        private String paymentToken;
        private String orderToken;
        private PayMethod paymentType;
        private Long paymentAmount;

        public Payment toEntity(){
            return Payment.builder()
                    .paymentToken(paymentToken)
                    .orderToken(orderToken)
                    .paymentType(paymentType.toString())
                    .paymentAmount(paymentAmount)
                    .build();
        }
    }
}
