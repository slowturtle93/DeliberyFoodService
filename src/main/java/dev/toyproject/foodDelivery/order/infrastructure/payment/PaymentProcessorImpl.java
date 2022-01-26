package dev.toyproject.foodDelivery.order.infrastructure.payment;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import dev.toyproject.foodDelivery.order.domain.payment.PaymentProcessor;
import dev.toyproject.foodDelivery.order.domain.payment.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {

    private final List<PaymentValidator> paymentValidatorList;
    private final List<PaymentApiCaller> paymentApiCallerList;

    /**
     * 주문 결제 요청
     *
     * @param order
     * @param paymentRequest
     */
    @Override
    public OrderInfo.OrderAPIPaymentResponse pay(Order order, OrderCommand.PaymentRequest paymentRequest) {
        paymentValidatorList.forEach(paymentValidator -> paymentValidator.validate(order, paymentRequest)); // 결제 요청 전 validation 체크
        PaymentApiCaller payApiCaller = routingApiCaller(paymentRequest.getPayMethod());                                   // 사용자가 선택한 결제 수단 Find
        return payApiCaller.pay(paymentRequest);                                                            // 사용자가 선택한 결제 수단에 결제 요청
    }

    /**
     * 주문 승인 요청
     */
    @Override
    public void approvePay(OrderCommand.PaymentApproveRequest paymentApproveRequest) {
        PaymentApiCaller payApiCaller = routingApiCaller(paymentApproveRequest.getPayMethod()); // 사용자가 선택한 결제 수단 Find
        payApiCaller.approvePay(paymentApproveRequest);
    }

    /**
     * 사용자가 선택한 결제 수단 찾기
     *
     * @param payMethod
     * @return
     */
    private PaymentApiCaller routingApiCaller(PayMethod payMethod){
        return paymentApiCallerList.stream()
                .filter(paymentApiCaller -> paymentApiCaller.support(payMethod))
                .findFirst()
                .orElseThrow(InvalidParamException::new);
    }
}
