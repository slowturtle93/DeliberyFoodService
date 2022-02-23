package dev.toyproject.foodDelivery.mapper.rider;

import dev.toyproject.foodDelivery.rider.domain.RiderCommand;
import dev.toyproject.foodDelivery.rider.domain.RiderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RiderMapper {
    List<RiderInfo.AvailableOrders> getOrdersAvailableDelivery(RiderCommand.RiderCurrentLocation command);
}
