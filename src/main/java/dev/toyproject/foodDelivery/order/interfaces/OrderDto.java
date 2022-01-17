package dev.toyproject.foodDelivery.order.interfaces;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
        private AddressFragment addressFragment;
        private List<RegisterOrderMenuRequest> orderMenuList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderMenuRequest{
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<RegisterOrderMenuOptionRequest> orderMenuOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderMenuOptionRequest{
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
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
        private List<OrderBasketMenuOptionGroupRequest> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderBasketMenuOptionGroupRequest{
        private Long id;
        private Integer ordering;
        private String menuOptionGroupName;
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
    @Builder
    @ToString
    public static class RegisterResponse {
        private final String orderToken;
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
