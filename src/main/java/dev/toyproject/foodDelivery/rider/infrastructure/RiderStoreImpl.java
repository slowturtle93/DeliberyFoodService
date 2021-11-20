package dev.toyproject.foodDelivery.rider.infrastructure;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.rider.domain.Rider;
import dev.toyproject.foodDelivery.rider.domain.RiderStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RiderStoreImpl implements RiderStore {

    private final RiderRepository riderRepository;

    @Override
    public Rider store(Rider rider) {
        if(StringUtils.isEmpty(rider.getRiderLoginId()))   throw new InvalidParamException("rider.getRiderLoginId()");
        if(StringUtils.isEmpty(rider.getRiderPwd()))       throw new InvalidParamException("rider.getRiderPwd()");
        if(StringUtils.isEmpty(rider.getRiderTel()))       throw new InvalidParamException("rider.getRiderTel()");
        if(StringUtils.isEmpty(rider.getRiderName()))      throw new InvalidParamException("rider.getRiderName()");
        if(StringUtils.isEmpty(rider.getRiderToken()))     throw new InvalidParamException("rider.getRiderToken()");
        if(StringUtils.isEmpty(rider.getResidence()))      throw new InvalidParamException("rider.getResidence()");
        if(StringUtils.isEmpty(rider.getDeliveryRegion())) throw new InvalidParamException("rider.getDeliveryRegion()");
        if(StringUtils.isEmpty(rider.getDeliveryMethod())) throw new InvalidParamException("rider.getDeliveryMethod()");
        if(rider.getStatus() == null)                      throw new InvalidParamException("rider.getStatus()");

        return riderRepository.save(rider);
    }
}
