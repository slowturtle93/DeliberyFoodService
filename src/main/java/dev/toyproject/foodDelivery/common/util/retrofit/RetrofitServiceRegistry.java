package dev.toyproject.foodDelivery.common.util.retrofit;

import dev.toyproject.foodDelivery.address.infrastructure.RetrofitKakaoAddressApi;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.RetrofitNaverSensApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RetrofitServiceRegistry {

    // Naver SENS API
    @Value("${spring.sens-api.url}")
    private String NaverSmsApi;

    // API Host 설정
    @Value("${spring.address-api.url}")
    private String kakaoAddressApi;
    
    // Retrofit Bean 생성
    @Bean
    public RetrofitNaverSensApi retrofitNaverSensApi(){
        var retrofit = RetrofitUtils.initRetrofit(NaverSmsApi);
        return retrofit.create(RetrofitNaverSensApi.class);
    }

    // Retrofit Bean 생성
    @Bean
    public RetrofitKakaoAddressApi retrofitKakaoAddressApi(){
        var retrofit = RetrofitUtils.initRetrofit(kakaoAddressApi);
        return retrofit.create(RetrofitKakaoAddressApi.class);
    }
}
