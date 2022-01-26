package dev.toyproject.foodDelivery.order.infrastructure.payment.kakao;

import dev.toyproject.foodDelivery.common.util.retrofit.RetrofitUtils;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import dev.toyproject.foodDelivery.order.domain.OrderRead;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import dev.toyproject.foodDelivery.order.infrastructure.payment.PaymentApiCaller;
import dev.toyproject.foodDelivery.owner.domain.Owner;
import dev.toyproject.foodDelivery.owner.domain.OwnerReader;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopReader;
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
    private final ShopReader shopReader;
    private final OwnerReader ownerReader;

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.KAKAO_PAY == payMethod;
    }

    /**
     * 카카오 페이 결제 준비 API 요청
     *
     * @param request
     * @return
     */
    @Override
    public OrderInfo.OrderAPIPaymentResponse pay(OrderCommand.PaymentRequest request) {
        Map<String, Object> params = new HashMap<>(); // request 정보

        Order order = orderRead.getOrder(request.getOrderToken());
        Shop shop = shopReader.getShopByToken(order.getShopToken());
        Owner owner = ownerReader.getOwnerByToken(shop.getOwnerToken());


        // request 정보 put
        params.put("cid"             , "TC0ONETIME"); // shop.getShopBusiness();
        params.put("partner_order_id", request.getOrderToken());
        params.put("partner_user_id" , owner.getOwnerLoginId());
        params.put("item_name"       , order.TotalMenuName());
        params.put("quantity"        , order.calculateTotalQuantity());
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

        return response.toConvert(request);
    }

    /**
     * 카카오페이 결제 승인 API 호출
     *
     * @param request
     */
    @Override
    public void approvePay(OrderCommand.PaymentApproveRequest request) {
        Map<String, Object> params = new HashMap<>(); // request 정보

        Order order = orderRead.getOrder(request.getOrderToken());
        Shop shop = shopReader.getShopByToken(order.getShopToken());
        Owner owner = ownerReader.getOwnerByToken(shop.getOwnerToken());

        // request 정보 put
        params.put("cid"              , "TC0ONETIME"); // shop.getShopBusiness();
        params.put("tid"              , request.getPaymentToken());
        params.put("partner_order_id" , order.getOrderToken());
        params.put("partner_user_id"  , owner.getOwnerLoginId());
        params.put("pg_token"         , request.getPgToken());

        // 결제 준비 요청
        var call = retrofitKakaoApi.kakaoPayApproveRequest(
                kakaoApiRequest.getAuthorization(),
                kakaoApiRequest.getContentType(),
                params);

        // API response
        KakaoApiResponse.approveResponse response =  retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }
}
