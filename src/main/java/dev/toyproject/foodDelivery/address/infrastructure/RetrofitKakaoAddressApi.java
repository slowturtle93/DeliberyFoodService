package dev.toyproject.foodDelivery.address.infrastructure;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitKakaoAddressApi {

    @Headers("Authorization: KakaoAK c3655b875b3b3e45c83933")
    @GET("v2/local/search/address.json?")
    Call<KakaoAddressResponse.response> KakaoAddressRequest(@Query("query") String query);
}
