package dev.toyproject.foodDelivery.order.infrastructure.payment.kakao;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@ToString
@Configuration
public class KakaoApiRequest {

    @Value("${payment.method.kakao.apiKey}")
    private String apiKey;

    @Value("${payment.method.kakao.partnerUserId}")
    private String partnerUserId;

    @Value("${payment.method.kakao.Authorization}")
    private String Authorization;

    @Value("${payment.method.kakao.ContentType}")
    private String ContentType;

    @Value("${payment.method.kakao.approvalUrl}")
    private String approvalUrl;

    @Value("${payment.method.kakao.cancelUrl}")
    private String cancelUrl;

    @Value("${payment.method.kakao.failUrl}")
    private String failUrl;
}
