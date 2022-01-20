package dev.toyproject.foodDelivery.order.infrastructure.payment.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class KakaoApiResponse {

    @Getter
    @Builder
    @ToString
    public static class response{
        private final String tid;
        private final String next_redirect_app_url;
        private final String next_redirect_mobile_url;
        private final String next_redirect_pc_url;
        private final String android_app_scheme;
        private final String ios_app_scheme;
        private final String created_at;
        private final String msg;
        private final String code;
    }
}
