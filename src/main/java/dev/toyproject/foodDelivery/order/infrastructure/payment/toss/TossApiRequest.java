package dev.toyproject.foodDelivery.order.infrastructure.payment.toss;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@ToString
@Configuration
public class TossApiRequest {

    @Value("${payment.method.toss.apiKey}")
    private String apiKey;

    @Value("${payment.method.toss.retUrl}")
    private String retUrl;

    @Value("${payment.method.toss.retCancelUrl}")
    private String retCancelUrl;

    @Value("${payment.method.toss.resultCallback}")
    private String resultCallback;

    @Value("${payment.method.toss.callbackVersion}")
    private String callbackVersion;
}
