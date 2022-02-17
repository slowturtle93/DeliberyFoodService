package dev.toyproject.foodDelivery.notification.common.domain;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.common.util.retrofit.RetrofitUtils;
import dev.toyproject.foodDelivery.notification.common.infrastructure.RetrofitCommonApi;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonApiServiceImpl implements CommonApiService{

    private final RetrofitCommonApi retrofitCommonApi;
    private final RetrofitUtils retrofitUtils;
    private static final String CONTENT_TYPE = "application/json";

    /**
     * 사장님 주문 확인 요청 API request
     *
     * @param request
     */
    @Override
    public void OwnerOrderConfirmApiRequest(OrderInfo.OrderPaymentConfirmRequest request) {

        JSONObject params = new JSONObject();
        params.put("ownerToken", request.getOwnerToken());

        var call = retrofitCommonApi.OwnerOrderConfirmRequest(CONTENT_TYPE, params);

        var response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * 주문 [주문 승인] 상태 변경
     *
     * @param orderToken
     */
    @Override
    public void OrderApprovalApiRequest(String orderToken) {
        JSONObject params = new JSONObject();
        params.put("orderToken", orderToken);

        var call = retrofitCommonApi.OrderApprovalApiRequest(CONTENT_TYPE, params);

        var response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * 주문 [주문 취소] 상태 변경 요청
     *
     * @param orderToken
     */
    @Override
    public void OrderCancelApiRequest(String orderToken) {
        JSONObject params = new JSONObject();
        params.put("orderToken", orderToken);

        var call = retrofitCommonApi.OrderCancelApiRequest(CONTENT_TYPE, params);

        var response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * 발행된 쿠폰 정보 조회
     *
     * @param couponIssueToken
     */
    @Override
    public CommonResponse CouponIssueApiRequest(String couponIssueToken) {

        var call = retrofitCommonApi.CouponIssueApiRequest(CONTENT_TYPE, couponIssueToken);

        var response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);

        return response;
    }

    /**
     * 결제 승인 후 발행한 쿠폰 상태 [USED] 변경
     *
     * @param couponIssueToken
     */
    @Override
    public void CouponIssueUsedApiRequest(String couponIssueToken) {
        JSONObject params = new JSONObject();
        params.put("couponIssueToken", couponIssueToken);

        var call = retrofitCommonApi.CouponIssueUsedApiRequest(CONTENT_TYPE, params);

        var response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }
}
