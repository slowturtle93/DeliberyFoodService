package dev.toyproject.foodDelivery.order.domain.kafka;

import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaOrderPaymentConsumer {

    @KafkaListener(topics = "order_payment", groupId = "khs", containerFactory = "orderPaymentListener")
    public void consume(OrderCommand.OrderPaymentConfirmRequest orderPaymentConfirmRequest) {
        log.info(orderPaymentConfirmRequest.getOrderToken());
        log.info(orderPaymentConfirmRequest.getPaymentToken());
    }
}
