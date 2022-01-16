package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;

import java.util.List;

public interface OrderMenuSeriesFactory {

    List<OrderMenu> store(Order order, OrderCommand.RegisterOrder registerOrder);
}
