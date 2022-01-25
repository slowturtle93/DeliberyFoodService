package dev.toyproject.foodDelivery.order.infrastructure.payment;

import dev.toyproject.foodDelivery.order.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
