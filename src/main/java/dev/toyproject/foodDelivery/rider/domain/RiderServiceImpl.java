package dev.toyproject.foodDelivery.rider.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService{

    private final RiderStore riderStore;

    /**
     * 라이더 등록
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public RiderInfo registerRider(RiderCommand command) {
        var initRider = command.toEntity();
        Rider rider = riderStore.store(initRider);
        return new RiderInfo(rider);
    }
}
