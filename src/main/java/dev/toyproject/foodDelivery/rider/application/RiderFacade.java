package dev.toyproject.foodDelivery.rider.application;

import dev.toyproject.foodDelivery.rider.domain.RiderCommand;
import dev.toyproject.foodDelivery.rider.domain.RiderInfo;
import dev.toyproject.foodDelivery.rider.domain.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderFacade {

    private final RiderService riderService;

    /**
     * 라이더 등록
     *
     * @param command
     * @return
     */
    public RiderInfo registerRider(RiderCommand command){
        var riderInfo = riderService.registerRider(command);
        return riderInfo;
    }
}
