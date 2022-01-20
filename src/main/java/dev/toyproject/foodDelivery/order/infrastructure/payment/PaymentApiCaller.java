package dev.toyproject.foodDelivery.order.infrastructure.payment;

import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;

public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);
    void pay(OrderCommand.PaymentRequest request);
}
