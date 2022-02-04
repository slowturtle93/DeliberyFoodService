package dev.toyproject.foodDelivery.notification.common.domain;

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
}
