package dev.toyproject.foodDelivery.notification.sms.domain;

import com.google.gson.Gson;
import dev.toyproject.foodDelivery.common.util.retrofit.RetrofitUtils;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.NaverSensDefalutRequest;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.NaverSensRequest;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.NaverSensResponse;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.RetrofitNaverSensApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSensServiceImpl implements NaverSensService {

    private final NaverSensKeyFactory naverSensKeyFactory;
    private final RetrofitNaverSensApi retrofitNaverSensApi;
    private final RetrofitUtils retrofitUtils;

    private final NaverSensDefalutRequest naverSensDefalutRequest;

    @Override
    public void sendNaverSens(NaverSensRequest.makeMessageInfo makeMessageInfo) {
        // 현재 서버 시간
        Long times = System.currentTimeMillis();

        // naver Sens signature 생성
        String signature = naverSensKeyFactory.makeSignature(
                naverSensDefalutRequest.getServiceId(),
                times.toString(),
                naverSensDefalutRequest.getAccessKey(),
                naverSensDefalutRequest.getSecretKey()
        );

        // Request Body
        NaverSensRequest naverSensRequest = NaverSensRequest.toRequest(
                naverSensDefalutRequest,
                NaverSensRequest.toMessage(makeMessageInfo.getMessageContent(), makeMessageInfo.getPhoneNumber()),
                makeMessageInfo.getContent());

        // request 직렬화
        Gson gson = new Gson();
        String body = gson.toJson(naverSensRequest);

        // Naver SENS API 요청
        var call = retrofitNaverSensApi.NaverSensRequest(
                MediaType.APPLICATION_JSON,
                times.toString(),
                naverSensDefalutRequest.getAccessKey(),
                signature,
                gson.fromJson(body, JSONObject.class));

        // Naver SENS API Response execute
        NaverSensResponse.response response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }
}
