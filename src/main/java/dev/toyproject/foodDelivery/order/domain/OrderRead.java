package dev.toyproject.foodDelivery.order.domain;

import java.util.List;

public interface OrderRead {
    Order getOrder(String orderToken);

    List<Order> getOrderList(String memberToken);
}
