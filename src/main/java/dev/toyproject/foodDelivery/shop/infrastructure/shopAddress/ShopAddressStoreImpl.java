package dev.toyproject.foodDelivery.shop.infrastructure.shopAddress;

import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddressStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopAddressStoreImpl implements ShopAddressStore {

    private final ShopAddressRepository shopAddressRepository;

    /**
     *  사장님 가게 등록
     *
     * @param shopAddress
     * @return
     */
    @Override
    public ShopAddress store(ShopAddress shopAddress) {
        return shopAddressRepository.save(shopAddress);
    }
}
