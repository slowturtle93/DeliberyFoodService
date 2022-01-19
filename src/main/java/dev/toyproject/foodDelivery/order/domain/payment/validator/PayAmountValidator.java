package dev.toyproject.foodDelivery.order.domain.payment.validator;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator{

    /**
     * 주문 메뉴의 가격 validation
     *
     * @param order
     * @param paymentRequest
     */
    @Override
    public void validate(Order order, OrderCommand.PaymentRequest paymentRequest) {
        if (!order.getTotalAmount().equals(paymentRequest.getAmount()))
            throw new InvalidParamException("주문가격이 불일치합니다");
    }
}
