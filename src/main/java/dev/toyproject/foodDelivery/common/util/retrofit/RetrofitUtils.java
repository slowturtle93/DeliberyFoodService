package dev.toyproject.foodDelivery.common.util.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RetrofitUtils {

    // 통신 로그 설정
    private static final HttpLoggingInterceptor loggingInterceptor
            = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY); // Response Body 부분 로그 기록

    // OkHttpClient 생성
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS);

    // Gson 객체 생성
    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    // Retrofit 초기 설정
    public static Retrofit initRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    // API Response body Return
    public <T> Optional<T> responseSync(Call<T> call) {
        try {
            Response<T> execute = call.execute();
            if (execute.isSuccessful()) {
                log.info("requestSync Body = {}", execute.body());
                return Optional.ofNullable(execute.body());
            } else {
                log.error("requestSync errorBody = {}", execute.errorBody());
                throw new RuntimeException("retrofit execute response error");
            }
        } catch (IOException e) {
            throw new RuntimeException("retrofit execute IOException");
        }
    }

    // API Response 성공 여부 확인
    public void responseVoid(Call<Void> call) {
        try {
            if (!call.execute().isSuccessful()) throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

