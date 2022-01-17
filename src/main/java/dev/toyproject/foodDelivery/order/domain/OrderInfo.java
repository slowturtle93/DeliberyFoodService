package dev.toyproject.foodDelivery.order.domain;

import lombok.*;

import java.util.List;

public class OrderInfo {

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
