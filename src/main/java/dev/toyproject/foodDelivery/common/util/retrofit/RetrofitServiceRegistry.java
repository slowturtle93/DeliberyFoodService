package dev.toyproject.foodDelivery.common.util.retrofit;

import dev.toyproject.foodDelivery.address.infrastructure.RetrofitKakaoAddressApi;
import dev.toyproject.foodDelivery.notification.common.infrastructure.RetrofitCommonApi;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.RetrofitNaverSensApi;
import dev.toyproject.foodDelivery.order.infrastructure.payment.kakao.RetrofitKakaoPayApi;
import dev.toyproject.foodDelivery.order.infrastructure.payment.toss.RetrofitTossPayApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RetrofitServiceRegistry {

    // COMMON API
    @Value("${spring.common-api.url}")
    private String CommonApi;

    // Naver SENS API
    @Value("${spring.sens-api.url}")
    private String NaverSmsApi;

    // API Host 설정
    @Value("${spring.address-api.url}")
    private String kakaoAddressApi;

    // kakaoPay Url
    @Value("${payment.method.kakao.url}")
    private String kakaoUPayUrl;

    // tossPay Url
    @Value("${payment.method.toss.url}")
    private String tossPayUrl;

    // Common Retrofit Bean 생성
    @Bean
    public RetrofitCommonApi retrofitCommonApi(){
        var retrofit = RetrofitUtils.initRetrofit(CommonApi);
        return retrofit.create(RetrofitCommonApi.class);
    }

    // naver snes Retrofit Bean 생성
    @Bean
    public RetrofitNaverSensApi retrofitNaverSensApi(){
        var retrofit = RetrofitUtils.initRetrofit(NaverSmsApi);
        return retrofit.create(RetrofitNaverSensApi.class);
    }

    // kakao address Retrofit Bean 생성
    @Bean
    public RetrofitKakaoAddressApi retrofitKakaoAddressApi(){
        var retrofit = RetrofitUtils.initRetrofit(kakaoAddressApi);
        return retrofit.create(RetrofitKakaoAddressApi.class);
    }

    // kakao pay Retrofit Bean 생성
    @Bean
    public RetrofitKakaoPayApi retrofitKakaoApi() {
        var retrofit = RetrofitUtils.initRetrofit(kakaoUPayUrl);
        return retrofit.create(RetrofitKakaoPayApi.class);
    }

    // TOSS pay Retrofit Bean 생성
    @Bean
    public RetrofitTossPayApi retrofitTossApi() {
        var retrofit = RetrofitUtils.initRetrofit(tossPayUrl);
        return retrofit.create(RetrofitTossPayApi.class);
    }
}
