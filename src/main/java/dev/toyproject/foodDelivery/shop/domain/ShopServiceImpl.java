package dev.toyproject.foodDelivery.shop.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

    private final ShopStore shopStore;
    private final ShopFactory shopFactory;

    /**
     * 사장님 가게 등록
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public String registerShop(ShopCommand.ShopRequest command) {
        var initShop = command.toEntity();
        Shop shop = shopStore.store(initShop);                          // 가게 등록
        shopFactory.shopBusinessStore(shop, command.getShopBusinessInfoRequest()); // 가게 사업자 정보 등록
        shopFactory.shopAddressStore(shop, command.getShopAddressInfoRequest());   // 가게 주소 정보 등록
        return shop.getShopToken();
    }
}
