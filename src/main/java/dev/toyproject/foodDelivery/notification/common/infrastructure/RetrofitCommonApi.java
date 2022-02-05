package dev.toyproject.foodDelivery.notification.common.infrastructure;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitCommonApi {

    @POST("v1/owner/order/push")
    Call<CommonResponse> OwnerOrderConfirmRequest(
            @Header("content-type") String contentType,
            @Body JSONObject body
    );

    @POST("v1/order/approval")
    Call<CommonResponse> OrderApprovalApiRequest(
            @Header("content-type") String contentType,
            @Body JSONObject body
    );

    @POST("v1/order/cancel")
    Call<CommonResponse> OrderCancelApiRequest(
            @Header("content-type") String contentType,
            @Body JSONObject body
    );
}
