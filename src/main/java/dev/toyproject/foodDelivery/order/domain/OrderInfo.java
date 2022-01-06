package dev.toyproject.foodDelivery.order.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class OrderInfo {

    @Getter
    @ToString
    public static class OrderBasketInfo{
        private String memberToken;
        private String shopToken;
        private OrderBasketMenuInfo orderBasketMenuInfo;

        public OrderBasketInfo(OrderCommand.OrderBasketRequest orderBasketCommand, OrderBasketMenuInfo orderBasketMenuInfo){
            this.memberToken         = orderBasketCommand.getMemberToken();
            this.shopToken           = orderBasketCommand.getShopToken();
            this.orderBasketMenuInfo = orderBasketMenuInfo;
        }
    }

    @Getter
    @ToString
    public static class OrderBasketMenuInfo{
        private Long id;
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private List<OrderBasketMenuOptionInfo> orderBasketMenuOptionInfoList;

        public OrderBasketMenuInfo(OrderCommand.OrderBasketMenuRequest orderBasketMenuCommand, List<OrderBasketMenuOptionInfo> orderBasketMenuOptionInfoList){
            this.id                            = orderBasketMenuCommand.getId();
            this.orderMenuName                 = orderBasketMenuCommand.getOrderMenuName();
            this.orderMenuCount                = orderBasketMenuCommand.getOrderMenuCount();
            this.orderMenuPrice                = orderBasketMenuCommand.getOrderMenuPrice();
            this.orderBasketMenuOptionInfoList = orderBasketMenuOptionInfoList;
        }
    }

    @Getter
    @ToString
    public static class OrderBasketMenuOptionInfo{
        private Long id;
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;

        public OrderBasketMenuOptionInfo(OrderCommand.OrderBasketMenuOptionRequest orderBasketMenuOptionCommand){
            this.id                         = orderBasketMenuOptionCommand.getId();
            this.orderMenuOptionName        = orderBasketMenuOptionCommand.getOrderMenuOptionName();
            this.orderMenuOptionPrice       = orderBasketMenuOptionCommand.getOrderMenuOptionPrice();
        }
    }
}
