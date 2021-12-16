package dev.toyproject.foodDelivery.shop.application;

import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopInfo;
import dev.toyproject.foodDelivery.shop.domain.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopFacade {

    private final ShopService shopService;

    /**
     * 사장님 가게 등록
     *
     * @param ShopCommand
     * @return
     */
    public String registerShop(ShopCommand.ShopRequest ShopCommand){
        var shopToken = shopService.registerShop(ShopCommand); // 가게 등록
        return shopToken;
    }

    /**
     * 사장님 가게 삭제
     *
     * @param shopToken
     */
    public void disableShop(String shopToken){
        shopService.disableShop(shopToken);
    }

    /**
     * 사장님 가게 정보 수정
     *
     * @param command
     * @return
     */
    public ShopInfo.Main updateShop(String shopToken, ShopCommand.ShopRequest command){
        var shopInfo = shopService.updateShop(shopToken, command);
        return shopInfo;
    }
}
