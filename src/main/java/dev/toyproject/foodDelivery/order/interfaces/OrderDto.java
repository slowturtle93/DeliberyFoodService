package dev.toyproject.foodDelivery.order.interfaces;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDto {

    /******************************** request ********************************/

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderRequest{
        private String memberToken;
        private String shopToken;
        private String paymentMethod;
        private Long totalAmount;
        private String region1DepthName;
        private String region2DepthName;
        private String region3DepthName;
        private String detail;
        private List<RegisterOrderMenuRequest> orderMenuList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderMenuRequest{
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private Integer ordering;
        private List<RegisterOrderMenuOptionGroupRequest> orderMenuOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderMenuOptionGroupRequest{
        private String orderMenuOptionGroupName;
        private Integer ordering;
        private List<RegisterOrderMenuOptionRequest> orderMenuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderMenuOptionRequest{
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
        private Integer ordering;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketRequest{
        private String memberToken;
        private String shopToken;
        private OrderBasketMenuRequest orderBasketMenu;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuRequest{
        private Long id;
        private Integer ordering;
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<OrderBasketMenuOptionGroupRequest> orderBasketMenuOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionGroupRequest{
        private Long id;
        private Integer ordering;
        private String orderMenuOptionGroupName;
        private List<OrderBasketMenuOptionRequest> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionRequest{
        private Long id;
        private Integer ordering;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
    }

    @Getter
    @Setter
    @ToString
    public static class PaymentRequest {
        @NotBlank(message = "orderToken 는 필수값입니다")
        private String orderToken;

        @NotNull(message = "payMethod 는 필수값입니다")
        private PayMethod payMethod;

        @NotNull(message = "amount 는 필수값입니다")
        private Long amount;
    }

    @Getter
    @Setter
    @ToString
    public static class orderPaymentTossCallbackRequest{
        private String status;              // PAY_COMPLETE (결제 완료)
        private String payToken;            // 승인된 결제 토큰
        private String orderNo;             // 결제생성 구간에서 전달된 가맹점 주문번호
        private String payMethod;           // 승인된 결제수단
        private Integer amount;             // 결제요청된 금액
        private Integer discountedAmount;   // 할인된 금액
        private Integer paidAmount;         // 지불수단 승인금액
        private String paidTs;              // 결제 완료 처리 시간
        private String transactionId;       // 거래 트랜잭션 아이디
        private Integer cardCompanyCode;    // 승인된 카드사 코드
        private String cardAuthorizationNo; // 카드 승인번호
        private String spreadOut;           // 사용자가 선택한 카드 할부개월
        private Boolean noInterest;         // 카드 무이자 적용 여부
        private String cardMethodType;      // 카드 타입
        private String cardNumber;          // 마스킹된 카드번호
        private String salesCheckLinkUrl;   // 신용카드 매출전표 호출 URL
    }

    /******************************** response ********************************/

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        private final String orderToken;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderResponse{
        private String orderToken;
        private String memberToken;
        private String shopToken;
        private String paymentMethod;
        private Long totalAmount;
        private String region1DepthName;
        private String region2DepthName;
        private String region3DepthName;
        private String detail;
        private String orderDate;
        private List<OrderMenuResponse> orderMenuList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderMenuResponse{
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private Integer ordering;
        private List<OrderMenuOptionGroupResponse> orderMenuOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderMenuOptionGroupResponse{
        private String orderMenuOptionGroupName;
        private Integer ordering;
        private List<OrderMenuOptionResponse> orderMenuOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderMenuOptionResponse{
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
        private Integer ordering;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketResponse{
        private String memberToken;
        private String shopToken;
        private OrderBasketMenuResponse orderBasketMenu;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuResponse{
        private Long id;
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<OrderBasketMenuOptionGroupResponse> orderBasketMenuOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionGroupResponse{
        private Long id;
        private String orderMenuOptionGroupName;
        private List<OrderBasketMenuOptionResponse> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionResponse{
        private Long id;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
    }

}
