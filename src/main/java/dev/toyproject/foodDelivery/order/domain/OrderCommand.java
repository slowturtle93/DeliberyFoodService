package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOption;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOptionGroup;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import lombok.*;

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
        private String region1DepthName;
        private String region2DepthName;
        private String region3DepthName;
        private String detail;
        private List<OrderCommand.RegisterOrderMenu> orderMenuList;

        public Order toEntity(){
            return Order.builder()
                    .memberToken(memberToken)
                    .shopToken(shopToken)
                    .paymentMethod(paymentMethod)
                    .detail(detail)
                    .totalAmount(totalAmount)
                    .region1DepthName(region1DepthName)
                    .region2DepthName(region2DepthName)
                    .region3DepthName(region3DepthName)
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
        private Integer ordering;
        private List<OrderCommand.RegisterOrderMenuOptionGroup> orderMenuOptionGroupList;

        public OrderMenu toEntity(Order order){
            return OrderMenu.builder()
                    .order(order)
                    .orderMenuName(orderMenuName)
                    .orderMenuCount(orderMenuCount)
                    .orderMenuPrice(orderMenuPrice)
                    .ordering(ordering)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderMenuOptionGroup{
        private String orderMenuOptionGroupName;
        private Integer ordering;
        private List<OrderCommand.RegisterOrderMenuOption> orderMenuOptionList;

        public OrderMenuOptionGroup toEntity(OrderMenu orderMenu){
            return OrderMenuOptionGroup.builder()
                    .orderMenu(orderMenu)
                    .orderMenuOptionGroupName(orderMenuOptionGroupName)
                    .ordering(ordering)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderMenuOption{
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
        private Integer ordering;

        public OrderMenuOption toEntity(OrderMenuOptionGroup orderMenuOptionGroup){
            return OrderMenuOption.builder()
                    .orderMenuOptionGroup(orderMenuOptionGroup)
                    .orderMenuOptionName(orderMenuOptionName)
                    .orderMenuOptionPrice(orderMenuOptionPrice)
                    .ordering(ordering)
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
        private String orderMenuOptionGroupName;
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

    @Getter
    @Builder
    @ToString
    public static class PaymentRequest{
        private final String    orderToken; // 주문토큰
        private final Long      amount;     // 결제금액
        private final PayMethod payMethod;  // 결제수단
    }

    @Getter
    @Builder
    @ToString
    public static class PaymentApproveRequest{
        private final String orderToken;   // 주문토큰
        private final String paymentToken; // 결제토큰
        private final String pgToken;      // 결제승인 요청을 인증하는 토큰(카카오페이)
        private final PayMethod payMethod; // 결제수단
    }

    @Getter
    @Builder
    @ToString
    public static class OrderPaymentStatusRequest{
        private String orderToken;
    }
}
