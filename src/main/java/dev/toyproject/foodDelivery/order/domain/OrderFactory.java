package dev.toyproject.foodDelivery.order.domain;

import java.util.List;

public interface OrderFactory {

    public String makeHashKey(OrderCommand.OrderBasketRequest command);

    public void orderBasketShopCheck(OrderCommand.OrderBasketRequest command);

    public OrderInfo.OrderBasketInfo duplicationMenu(OrderCommand.OrderBasketRequest command, String hashKey);

    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken);

    public void registerCacheMenuBasket(String memberToken, String hashKey, OrderCommand.OrderBasketRequest command);

    public void removeMenuBasket(String memberToken, String hashKey);
}
