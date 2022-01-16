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
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<OrderBasketMenuOptionRequest> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionRequest{
        private Long id;
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
