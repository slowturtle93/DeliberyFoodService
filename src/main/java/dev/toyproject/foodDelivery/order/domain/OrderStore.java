package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOption;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOptionGroup;

public interface OrderStore {
    Order store(Order order);
    OrderMenu store(OrderMenu orderMenu);
    OrderMenuOptionGroup store(OrderMenuOptionGroup orderMenuOptionGroup);
    OrderMenuOption store(OrderMenuOption orderMenuOption);
}
