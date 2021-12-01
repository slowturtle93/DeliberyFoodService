package dev.toyproject.foodDelivery.notification.sms.infrastructure;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ToString
public class NaverSensDefalutRequest {

    @Value("${spring.sens-api.accessKey}")
    private String accessKey;

    @Value("${spring.sens-api.secretKey}")
    private String secretKey;

    @Value("${spring.sens-api.from}")
    private String from;

    @Value("${spring.sens-api.type}")
    private String type;

    @Value("${spring.sens-api.contentType}")
    private String contentType;

    @Value("${spring.sens-api.countryCode}")
    private String countryCode;

    @Value("${spring.sens-api.serviceId}")
    private String serviceId;

}