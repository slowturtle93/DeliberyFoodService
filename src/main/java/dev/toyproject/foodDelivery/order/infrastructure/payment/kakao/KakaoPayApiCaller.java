package dev.toyproject.foodDelivery.order.infrastructure.payment.kakao;

import dev.toyproject.foodDelivery.common.util.retrofit.RetrofitUtils;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderRead;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import dev.toyproject.foodDelivery.order.infrastructure.payment.PaymentApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoPayApiCaller implements PaymentApiCaller {

    private final RetrofitKakaoPayApi retrofitKakaoApi;
    private final RetrofitUtils retrofitUtils;
    private final KakaoApiRequest kakaoApiRequest;
    private final OrderRead orderRead;

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.KAKAO_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        Map<String, Object> params = new HashMap<>(); // request 정보
        long quantity = 0;                         // 주문 총 수량
        StringBuilder menuName = new StringBuilder(); // 주문 메뉴명

        Order order = orderRead.getOrder(request.getOrderToken());

        // 주문 수량 및 상품명
        for(OrderMenu orderMenu : order.getOrderMenuList()){
            quantity += orderMenu.getOrderMenuCount();
            menuName.append(orderMenu.getOrderMenuName() + ",");
        }

        // request 정보 put
        params.put("cid"             , kakaoApiRequest.getApiKey());
        params.put("partner_order_id", request.getOrderToken());
        params.put("partner_user_id" , kakaoApiRequest.getPartnerUserId());
        params.put("item_name"       , menuName.toString());
        params.put("quantity"        , quantity);
        params.put("total_amount"    , request.getAmount());
        params.put("tax_free_amount" , 0);
        params.put("approval_url"    , kakaoApiRequest.getApprovalUrl());
        params.put("cancel_url"      , kakaoApiRequest.getCancelUrl());
        params.put("fail_url"        , kakaoApiRequest.getFailUrl());

        // 결제 준비 요청
        var call = retrofitKakaoApi.kakaoPayRequest(
                kakaoApiRequest.getAuthorization(),
                kakaoApiRequest.getContentType(),
                params);

        // API response
        KakaoApiResponse.response response =  retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }
}
