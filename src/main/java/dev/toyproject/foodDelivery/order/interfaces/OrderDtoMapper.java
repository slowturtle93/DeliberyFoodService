package dev.toyproject.foodDelivery.order.interfaces;

import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel       = "spring",
        injectionStrategy    = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    OrderCommand.OrderBasketRequest of(OrderDto.OrderBasketRequest request);
}
