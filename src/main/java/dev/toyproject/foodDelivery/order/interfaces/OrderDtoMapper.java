package dev.toyproject.foodDelivery.order.interfaces;

import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel       = "spring",
        injectionStrategy    = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    /******************************** request ********************************/

    OrderCommand.OrderBasketRequest of(OrderDto.OrderBasketRequest request);

    OrderCommand.RegisterOrder of(OrderDto.RegisterOrderRequest request);

    /******************************** response ********************************/

    List<OrderDto.OrderBasketResponse> orderMenuListResponse(List<OrderInfo.OrderBasketInfo> response);

    OrderDto.RegisterResponse of(String orderToken);

    @Mappings({@Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    OrderDto.OrderResponse of(OrderInfo.OrderResponse response);

    @Mappings({@Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")})
    List<OrderDto.OrderResponse> orderInfoList(List<OrderInfo.OrderResponse> response);
}
