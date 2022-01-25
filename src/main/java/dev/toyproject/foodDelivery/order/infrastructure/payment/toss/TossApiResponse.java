package dev.toyproject.foodDelivery.order.infrastructure.payment.toss;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
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

        public OrderInfo.OrderAPIPaymentResponse toConvert(OrderCommand.PaymentRequest request){
            return OrderInfo.OrderAPIPaymentResponse.builder()
                    .paymentToken(payToken)
                    .orderToken(request.getOrderToken())
                    .paymentType(request.getPayMethod().toString())
                    .paymentAmount(request.getAmount())
                    .redirectUrl(checkoutPage)
                    .build();
        }
    }
}
