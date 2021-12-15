package dev.toyproject.foodDelivery.shop.infrastructure.shopBusiness;

import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusinessStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopBusinessStoreImpl implements ShopBusinessStore {

    private final ShopBusinessRepository shopBusinessRepository;

    /**
     * 가게 사업자 정보 등록
     *
     * @param shopBusiness
     * @return
     */
    @Override
    public ShopBusiness store(ShopBusiness shopBusiness) {
        return shopBusinessRepository.save(shopBusiness);
    }
}
