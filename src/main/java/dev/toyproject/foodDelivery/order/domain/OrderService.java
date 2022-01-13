package dev.toyproject.foodDelivery.order.domain;

import java.util.List;

public interface OrderService {

    public List<OrderCommand.OrderBasketRequest> registerMenuBasket(OrderCommand.OrderBasketRequest command);

    public List<OrderCommand.OrderBasketRequest> deleteMenuBasket(OrderCommand.OrderBasketRequest command);

    public void deleteMenuBasketAll(String memberToken);

    public List<OrderCommand.OrderBasketRequest> retrieveMenuBasket(String memberToken);
}
