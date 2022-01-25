package dev.toyproject.foodDelivery.order.infrastructure.payment;

import dev.toyproject.foodDelivery.order.domain.payment.Payment;
import dev.toyproject.foodDelivery.order.domain.payment.PaymentStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentStoreImpl implements PaymentStore {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment store(Payment payment) {
        return paymentRepository.save(payment);
    }
}
