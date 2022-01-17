package dev.toyproject.foodDelivery.order.domain;

import lombok.*;

import java.util.List;

public class OrderCommand {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class OrderBasketRequest{
        private String memberToken;
        private String shopToken;
        private OrderBasketMenuRequest orderBasketMenu;
    }

    @Getter
    @Setter
    @Builder
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
    @Builder
    @ToString
    public static class OrderBasketMenuOptionGroupRequest{
        private Long id;
        private Integer ordering;
        private String menuOptionGroupName;
        private List<OrderBasketMenuOptionRequest> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class OrderBasketMenuOptionRequest{
        private Long id;
        private Integer ordering;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
    }
}
