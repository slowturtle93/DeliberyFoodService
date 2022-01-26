package dev.toyproject.foodDelivery.order.infrastructure.payment;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.order.domain.payment.Payment;
import dev.toyproject.foodDelivery.order.domain.payment.PaymentRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentReadImpl implements PaymentRead {

    private final PaymentRepository paymentRepository;

    /**
     * 결제 정보 조회
     *
     * @param paymentToken
     * @return
     */
    @Override
    public Payment getPayment(String paymentToken) {
        return paymentRepository.findByPaymentToken(paymentToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
