package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopFactory;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddressStore;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusinessStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopFactoryImpl implements ShopFactory {

    private final ShopBusinessStore shopBusinessStore;
    private final ShopAddressStore shopAddressStore;

    /**
     * 사업자 정보 등록
     *
     * @param shop
     * @return
     */
    @Override
    public ShopBusiness shopBusinessStore(Shop shop, ShopCommand.ShopBusinessRequest command) {
        var shopBusinessRequest = command;
        var initShopBusiness = shopBusinessRequest.toEntity(shop);
        return shopBusinessStore.store(initShopBusiness);
    }

    /**
     * 사장님 가게 주소 등록
     *
     * @param shop
     * @param command
     * @return
     */
    @Override
    public ShopAddress shopAddressStore(Shop shop, ShopCommand.ShopAddressRequest command) {
        var shopAddressRequest = command;
        var initShopAddress = shopAddressRequest.toEntity(shop);
        return shopAddressStore.store(initShopAddress);
    }
}
