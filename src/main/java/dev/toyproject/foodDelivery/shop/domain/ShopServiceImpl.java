package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
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
    private final ShopReader shopReader;

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

    /**
     * 사장님 가게 삭제
     *
     * @param shopToken
     */
    @Override
    @Transactional
    public void disableShop(String shopToken) {
        Shop shop = shopReader.getShopByToken(shopToken);
        shop.disable();
    }

    /**
     * 사장님 가게 정보 수정
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public ShopInfo.Main updateShop(String shopToken, ShopCommand.ShopRequest command) {
        Shop shop = shopReader.getShopByToken(shopToken);
        ShopAddress shopAddress = shop.getShopAddress();
        shop.updateShopInfo(command);
        shopAddress.updateAddress(command.getShopAddressInfoRequest().getAddressFragment(), command.getShopAddressInfoRequest().getDetail());
        return shopReader.getShopSeries(shop);
    }
}
