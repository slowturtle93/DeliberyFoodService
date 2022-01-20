package dev.toyproject.foodDelivery.order.domain;

import java.util.List;

public interface OrderService {

    public List<OrderInfo.OrderBasketInfo> registerMenuBasket(OrderCommand.OrderBasketRequest command);

    public List<OrderInfo.OrderBasketInfo> deleteMenuBasket(OrderCommand.OrderBasketRequest command);

    public void deleteMenuBasketAll(String memberToken);

    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken);

    public List<OrderInfo.OrderBasketInfo> updateMenuBasketAmount (OrderCommand.OrderBasketRequest command);

    public String registerOrder(OrderCommand.RegisterOrder registerOrder);

    public OrderInfo.OrderResponse retrieveOrder(String orderToken);

    public List<OrderInfo.OrderResponse> retrieveOrderList(String memberToken);

    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);
}
