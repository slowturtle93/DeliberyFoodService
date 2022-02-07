package dev.toyproject.foodDelivery.order.infrastructure.payment;

import dev.toyproject.foodDelivery.order.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentToken(String paymentToken);

    Optional<Payment> findByOrderToken(String orderToken);
}
