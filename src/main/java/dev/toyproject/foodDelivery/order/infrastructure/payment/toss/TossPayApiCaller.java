package dev.toyproject.foodDelivery.order.infrastructure.payment.toss;

import dev.toyproject.foodDelivery.common.util.retrofit.RetrofitUtils;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import dev.toyproject.foodDelivery.order.domain.payment.PaymentRead;
import dev.toyproject.foodDelivery.order.infrastructure.payment.PaymentApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPayApiCaller implements PaymentApiCaller {

    private final RetrofitTossPayApi retrofitTossPayApi;
    private final RetrofitUtils retrofitUtils;
    private final TossApiRequest tossApiRequest;
    private final PaymentRead paymentRead;

    /**
     * 결제 방식 TOSS_PAY CHECK
     *
     * @param payMethod
     * @return
     */
    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.TOSS_PAY == payMethod;
    }

    /**
     * Toss Pay 결제 요청 api 호출
     *
     * @param request
     */
    @Override
    public OrderInfo.OrderAPIPaymentResponse pay(OrderCommand.PaymentRequest request) {

        // TODO - 구현
        JSONObject params = new JSONObject();
        params.put("orderNo", request.getOrderToken());
        params.put("amount", request.getAmount());
        params.put("amountTaxFree", 0);
        params.put("productDesc", "상품 결제 요청");
        params.put("apiKey", tossApiRequest.getApiKey());
        params.put("autoExecute", true);
        params.put("resultCallback", tossApiRequest.getResultCallback());
        params.put("callbackVersion", tossApiRequest.getCallbackVersion());
        params.put("retUrl", tossApiRequest.getRetUrl());
        params.put("retCancelUrl", tossApiRequest.getRetCancelUrl());

        var call = retrofitTossPayApi.tossPayRequest(params);

        // API response
        TossApiResponse.response response =  retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);

        return response.toConvert(request);
    }

    /**
     * toss pay 결제 승인 API 호출
     *
     * @param request
     */
    @Override
    public void approvePay(OrderCommand.PaymentApproveRequest request) {
        // TODO - 구현
    }

    /**
     * toss pay 결제 취소 API 호출
     *
     * @param request
     */
    @Override
    public void cancelPay(OrderCommand.PaymentCancelRequest request) {
        // TODO - 구현
        var payment = paymentRead.getPayment(request.getPaymentToken());

        JSONObject params = new JSONObject();
        params.put("apiKey"  , tossApiRequest.getApiKey());
        params.put("payToken", request.getPaymentToken());

        var call = retrofitTossPayApi.tossPayRefundRequest(params);

        // API response
        TossApiResponse.refundResponse response =  retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }
}
