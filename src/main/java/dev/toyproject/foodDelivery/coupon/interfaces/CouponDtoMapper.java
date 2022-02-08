package dev.toyproject.foodDelivery.coupon.interfaces;

import dev.toyproject.foodDelivery.coupon.domain.CouponCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel       = "spring",
        injectionStrategy    = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CouponDtoMapper {

    /******************************** request ********************************/

    CouponCommand.Register of(CouponDto.Register request);
}
