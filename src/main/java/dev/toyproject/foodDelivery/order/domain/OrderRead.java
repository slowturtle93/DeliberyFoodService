package dev.toyproject.foodDelivery.order.domain;

public interface OrderRead {
    Order getOrder(String orderToken);
}
