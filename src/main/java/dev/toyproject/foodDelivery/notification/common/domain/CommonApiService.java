package dev.toyproject.foodDelivery.notification.common.domain;

import dev.toyproject.foodDelivery.order.domain.OrderInfo;

public interface CommonApiService {
    void OwnerOrderConfirmApiRequest(OrderInfo.OrderPaymentConfirmRequest request);

    void OrderApprovalApiRequest(String orderToken);

    void OrderCancelApiRequest(String orderToken);
}
