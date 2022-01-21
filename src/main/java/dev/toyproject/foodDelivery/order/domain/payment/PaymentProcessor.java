package dev.toyproject.foodDelivery.order.domain.payment;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;

public interface PaymentProcessor {
    OrderInfo.OrderPaymentRedirectUrl pay(Order order, OrderCommand.PaymentRequest paymentRequest);
}
