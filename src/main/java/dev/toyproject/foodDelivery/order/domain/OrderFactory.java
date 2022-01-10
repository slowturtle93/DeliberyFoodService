package dev.toyproject.foodDelivery.order.domain;

public interface OrderFactory {

    public String makeHashKey(OrderCommand.OrderBasketRequest command);

    public void orderBasketShopCheck(OrderCommand.OrderBasketRequest command);

    public OrderCommand.OrderBasketRequest duplicationMenu(OrderCommand.OrderBasketRequest command, String hashKey);
}
