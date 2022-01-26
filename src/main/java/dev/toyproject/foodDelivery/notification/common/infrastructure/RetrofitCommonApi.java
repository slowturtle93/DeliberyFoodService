package dev.toyproject.foodDelivery.notification.common.infrastructure;

import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitCommonApi {

    @POST("v1/owner/order/push")
    Call<Object> OwnerOrderConfirmRequest(
            @Header("content-type") String contentType,
            @Body JSONObject body
    );
}
