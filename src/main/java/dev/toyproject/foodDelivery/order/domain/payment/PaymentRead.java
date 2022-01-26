package dev.toyproject.foodDelivery.order.domain.payment;

public interface PaymentRead {
    Payment getPayment(String paymentToken);
}
