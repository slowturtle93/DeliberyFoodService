package dev.toyproject.foodDelivery.order.domain;

import java.util.List;

public interface OrderService {

    public List<OrderCommand.OrderBasketRequest> registerMenuBasket(OrderCommand.OrderBasketRequest command);
}
