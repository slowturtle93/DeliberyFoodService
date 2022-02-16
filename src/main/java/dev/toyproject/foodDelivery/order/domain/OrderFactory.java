package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import dev.toyproject.foodDelivery.order.domain.payment.Payment;

import java.util.List;

public interface OrderFactory {

    public String makeHashKey(OrderCommand.OrderBasketRequest command);

    public void orderBasketShopCheck(OrderCommand.OrderBasketRequest command);

    public OrderCommand.OrderBasketRequest duplicationMenu(OrderCommand.OrderBasketRequest command, String hashKey);

    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken);

    public void registerCacheMenuBasket(OrderCommand.OrderBasketRequest command, String hashKey);

    public void removeMenuBasket(String memberToken, String hashKey);

    public List<OrderInfo.OrderBasketInfo> updateMenuBasketAmount(OrderCommand.OrderBasketRequest command, String hashKey);

    public void removeMenuBasketAll(String memberToken);

    public void setRedisCacheOrderPaymentToken(String orderToken, String paymentToken);

    public List<OrderMenu> store(Order order, OrderCommand.RegisterOrder registerOrder);

    public OrderCommand.PaymentApproveRequest approveRequestConvertPayment(Payment payment, String pgToken);

    public OrderInfo.OrderPaymentConfirmRequest orderPaymentConfirmInfo(String paymentToken);

    public void orderPriceValidator(OrderCommand.RegisterOrder command);
}
