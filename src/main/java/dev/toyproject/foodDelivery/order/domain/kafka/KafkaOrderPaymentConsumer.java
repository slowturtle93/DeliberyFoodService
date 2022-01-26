package dev.toyproject.foodDelivery.order.domain.kafka;

import dev.toyproject.foodDelivery.notification.common.domain.CommonApiService;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaOrderPaymentConsumer {

    private final CommonApiService commonApiService;

    @KafkaListener(topics = "order_payment", groupId = "khs", containerFactory = "orderPaymentListener")
    public void orderPaymentConsume(OrderInfo.OrderPaymentConfirmRequest orderPaymentConfirmRequest) {
        commonApiService.OwnerOrderConfirmApiRequest(orderPaymentConfirmRequest);
    }
}
