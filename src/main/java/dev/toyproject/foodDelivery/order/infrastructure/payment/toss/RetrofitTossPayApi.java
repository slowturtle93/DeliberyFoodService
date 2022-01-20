package dev.toyproject.foodDelivery.order.infrastructure.payment.toss;

import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitTossPayApi {

    @Headers("Content-Type: application/json")
    @POST("api/v2/payments")
    Call<TossApiResponse.response> tossPayRequest(@Body JSONObject jsonBody);
}
