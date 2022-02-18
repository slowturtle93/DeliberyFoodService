package dev.toyproject.foodDelivery.notification.common.infrastructure;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.*;

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

    @GET("v1/coupon/issue/{couponIssueToken}")
    Call<CommonResponse> CouponIssueApiRequest(
            @Header("content-type") String contentType,
            @Path("couponIssueToken") String couponIssueToken
    );

    @POST("v1/coupon/issue/used")
    Call<CommonResponse> CouponIssueUsedApiRequest(
            @Header("content-type") String contentType,
            @Body JSONObject body
    );

    @POST("v1/coupon/issue/init")
    Call<CommonResponse> CouponIssueInitApiRequest(
            @Header("content-type") String contentType,
            @Body JSONObject body
    );

}
