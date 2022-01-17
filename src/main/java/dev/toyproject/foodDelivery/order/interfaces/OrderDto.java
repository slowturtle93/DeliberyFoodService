package dev.toyproject.foodDelivery.order.interfaces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class OrderDto {

    /******************************** request ********************************/

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

    /******************************** response ********************************/

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
        private Integer ordering;
        private List<OrderBasketMenuOptionGroupResponse> orderBasketMenuOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionGroupResponse{
        private Long id;
        private String orderMenuOptionGroupName;
        private Integer ordering;
        private List<OrderBasketMenuOptionResponse> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionResponse{
        private Long id;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
        private Integer ordering;
    }

}
