package dev.toyproject.foodDelivery.notification.sms.infrastructure;

import org.springframework.http.MediaType;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitNaverSensApi {

    @POST("/sms/v2/services/ncp:sms:kr:26752:fooddelpjt/messages")
    Call<NaverSensResponse.response> NaverSensRequest(
            @Header("Content-Type") MediaType contentType,
            @Header("x-ncp-apigw-timestamp")    String Timestamp,
            @Header("x-ncp-iam-access-key")     String accessKey,
            @Header("x-ncp-apigw-signature-v2") String signature,
            @Body JSONObject body
    );
}
