package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOption;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class OrderCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOrder{
        private String memberToken;
        private String shopToken;
        private String paymentMethod;
        private Long totalAmount;
        private AddressFragment addressFragment;
        private List<OrderCommand.RegisterOrderMenu> orderMenuList;

        public Order toEntity(){
            return Order.builder()
                    .memberToken(memberToken)
                    .shopToken(shopToken)
                    .paymentMethod(paymentMethod)
                    .totalAmount(totalAmount)
                    .addressFragment(addressFragment)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderMenu{
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;

        public OrderMenu toEntity(Order order){
            return OrderMenu.builder()
                    .order(order)
                    .orderMenuName(orderMenuName)
                    .orderMenuCount(orderMenuCount)
                    .orderMenuPrice(orderMenuPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderMenuOption{
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;

        public OrderMenuOption toEntity(OrderMenu orderMenu){
            return OrderMenuOption.builder()
                    .orderMenu(orderMenu)
                    .orderMenuOptionName(orderMenuOptionName)
                    .orderMenuOptionPrice(orderMenuOptionPrice)
                    .build();
        }
    }

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
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<OrderBasketMenuOptionRequest> orderBasketMenuOptionList;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class OrderBasketMenuOptionRequest{
        private Long id;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
    }
}
