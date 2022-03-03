package dev.toyproject.foodDelivery.address.application;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.address.domain.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressFacade {

    private final AddressService addressService;

    /**
     * 주소 검색
     *
     * @param query
     * @return
     */
    public AddressFragment getAddress(String query){
        return addressService.searchAddress(query);
    }
}
