package dev.toyproject.foodDelivery.order.infrastructure.payment.kakao;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

public class KakaoApiResponse {

    @Getter
    @Builder
    @ToString
    public static class response{
        private final String tid;
        private final String next_redirect_app_url;
        private final String next_redirect_mobile_url;
        private final String next_redirect_pc_url;
        private final String android_app_scheme;
        private final String ios_app_scheme;
        private final String created_at;
        private final String msg;
        private final String code;

        public OrderInfo.OrderAPIPaymentResponse toConvert(OrderCommand.PaymentRequest request){
            return OrderInfo.OrderAPIPaymentResponse.builder()
                    .paymentToken(tid)
                    .orderToken(request.getOrderToken())
                    .paymentType(request.getPayMethod().toString())
                    .paymentAmount(request.getAmount())
                    .redirectUrl(next_redirect_mobile_url)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class approveResponse{
        private final String aid;
        private final String tid;
        private final String cid;
        private final String sid;
        private final String partner_order_id;
        private final String partner_user_id;
        private final String payment_method_type;
        private final String item_name;
        private final String item_code;
        private final Integer quantity;
        private final String created_at;
        private final String approved_at;
        private final String payload;
        private final Amount amount;
        private final CardInfo card_info;
    }

    @Getter
    @Builder
    public static class Amount{
        private final Integer total;
        private final Integer tax_free;
        private final Integer vat;
        private final Integer point;
        private final Integer discount;
    }

    @Getter
    @Builder
    public static class CardInfo{
        private final String purchase_corp;
        private final String purchase_corp_code;
        private final String issuer_corp;
        private final String issuer_corp_code;
        private final String kakaopay_purchase_corp;
        private final String kakaopay_purchase_corp_code;
        private final String kakaopay_issuer_corp;
        private final String kakaopay_issuer_corp_code;
        private final String bin;
        private final String card_type;
        private final String install_month;
        private final String approved_id;
        private final String card_mid;
        private final String interest_free_install;
        private final String card_item_code;
    }
}
