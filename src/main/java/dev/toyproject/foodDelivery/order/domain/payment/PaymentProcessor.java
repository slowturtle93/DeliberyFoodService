package dev.toyproject.foodDelivery.order.domain.payment;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;

public interface PaymentProcessor {
    OrderInfo.OrderAPIPaymentResponse pay(Order order, OrderCommand.PaymentRequest paymentRequest);

    void approvePay(OrderCommand.PaymentApproveRequest paymentRequest);

    void cancelPay(OrderCommand.PaymentCancelRequest paymentRequest);
}
