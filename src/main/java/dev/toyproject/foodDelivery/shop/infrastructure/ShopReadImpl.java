package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopInfo;
import dev.toyproject.foodDelivery.shop.domain.ShopReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopReadImpl implements ShopReader {

    private final ShopRepository shopRepository;

    /**
     * 특정 가게 조회 By shopToken
     *
     * @param shopToken
     * @return
     */
    @Override
    public Shop getShopByToken(String shopToken) {
        return shopRepository.findByShopTokenAndStatus(shopToken, Shop.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 가게 정보 series
     *
     * @param shop
     * @return
     */
    @Override
    public ShopInfo.Main getShopSeries(Shop shop) {
        var shopBusinessInfo = new ShopInfo.ShopBusinessInfo(shop.getShopBusiness());
        var shopAddressInfo = new ShopInfo.ShopAddressInfo(shop.getShopAddress());

        return new ShopInfo.Main(shop, shopBusinessInfo, shopAddressInfo);
    }
}
