package dev.toyproject.foodDelivery.order.domain.payment;

import dev.toyproject.foodDelivery.order.domain.payment.Payment;

public interface PaymentStore {
    Payment store(Payment payment);
}
