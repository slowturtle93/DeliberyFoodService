package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.order.domain.payment.PaymentCommand;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderInfoMapper {

    @Mappings({@Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    OrderInfo.OrderResponse of(Order order);

    @Mappings({@Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    List<OrderInfo.OrderResponse> orderInfoList(List<Order> order);

    PaymentCommand.RegisterPayment of(OrderInfo.OrderAPIPaymentResponse response);
}
