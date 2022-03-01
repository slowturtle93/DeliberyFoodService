package dev.toyproject.foodDelivery.rider.interfaces;

import dev.toyproject.foodDelivery.rider.domain.RiderCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel       = "spring",
        injectionStrategy    = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RiderDtoMapper {

    /************************ request ************************/

    RiderCommand.RiderCurrentLocation of(RiderDto.RiderCurrentLocation request);

    RiderCommand.RiderOrderMenuCommand of(RiderDto.RiderOrderMenuRequest request);
}
