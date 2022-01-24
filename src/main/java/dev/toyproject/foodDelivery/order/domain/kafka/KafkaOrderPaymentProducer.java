package dev.toyproject.foodDelivery.order.domain.kafka;

import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaOrderPaymentProducer {

    private static final String TOPIC = "order_payment";
    private final KafkaTemplate<String, OrderCommand.OrderPaymentConfirmRequest> kafkaOrderPaymentTemplate;

    @Autowired
    public KafkaOrderPaymentProducer(KafkaTemplate<String, OrderCommand.OrderPaymentConfirmRequest> kafkaOrderPaymentTemplate) {
        this.kafkaOrderPaymentTemplate = kafkaOrderPaymentTemplate;
    }

    public void registerKafkaMessage(OrderCommand.OrderPaymentConfirmRequest APIResponse) {

        // Send a message
        try{
            kafkaOrderPaymentTemplate.send(TOPIC, APIResponse);
            log.info("send kafka message");
        }catch (Exception e){
            log.info("fail kafka message");
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}
