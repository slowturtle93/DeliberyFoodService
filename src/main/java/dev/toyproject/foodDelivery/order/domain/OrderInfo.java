package dev.toyproject.foodDelivery.order.domain;

import lombok.*;

import java.util.List;

public class OrderInfo {

    @Getter
    @Builder
    @ToString
    public static class OrderResponse{
        private Long id;
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
        private Order.Status status;
        private List<OrderInfo.OrderMenuResponse> orderMenuList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderMenuResponse{
        private Long id;
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private Integer ordering;
        private List<OrderInfo.OrderMenuOptionGroupResponse> orderMenuOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderMenuOptionGroupResponse{
        private Long id;
        private String orderMenuOptionGroupName;
        private Integer ordering;
        private List<OrderInfo.OrderMenuOptionResponse> orderMenuOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderMenuOptionResponse{
        private Long id;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
        private Integer ordering;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderAPIPaymentResponse{
        private String paymentToken;
        private String orderToken;
        private String paymentType;
        private Long paymentAmount;
        private String redirectUrl;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderPaymentRedirectUrl{
        private String redirectUrl;
    }

    /*********** 장바구니 ***********/

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderBasketInfo{
        private String memberToken;
        private String shopToken;
        private OrderBasketMenuInfo orderBasketMenu;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderBasketMenuInfo{
        private Long id;
        private Integer ordering;
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<OrderBasketMenuOptionGroupInfo> orderBasketMenuOptionGroupList;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderBasketMenuOptionGroupInfo{
        private Long id;
        private Integer ordering;
        private String orderMenuOptionGroupName;
        List<OrderBasketMenuOptionInfo> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderBasketMenuOptionInfo{
        private Long id;
        private Integer ordering;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
    }
}
