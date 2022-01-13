package dev.toyproject.foodDelivery.order.domain;

import java.util.List;

public interface OrderService {

    public List<OrderInfo.OrderBasketInfo> registerMenuBasket(OrderCommand.OrderBasketRequest command);

    public List<OrderInfo.OrderBasketInfo> deleteMenuBasket(OrderCommand.OrderBasketRequest command);

    public void deleteMenuBasketAll(String memberToken);

    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken);
}
