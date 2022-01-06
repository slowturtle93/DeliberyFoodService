package dev.toyproject.foodDelivery.order.domain;

public interface OrderFactory {

    public String makeHashKey(OrderCommand.OrderBasketRequest command);
}
