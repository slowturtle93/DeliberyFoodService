package dev.toyproject.foodDelivery.shop.application;

import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopInfo;
import dev.toyproject.foodDelivery.shop.domain.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 메뉴 등록
     *
     * @param shopToken
     * @param command
     * @return
     */
    public String registerMenu(String shopToken, List<ShopCommand.MenuGroupRequest> command){
        return shopService.registerMenu(shopToken, command);
    }

    /**
     * 메뉴 수정
     *
     * @param command
     */
    public void updateMenu(List<ShopCommand.MenuGroupRequest> command){
        shopService.updateMenu(command);
    }

    /**
     * 메뉴 그룹 삭제
     *
     * @param command
     */
    public void deleteMenuGroup(ShopCommand.MenuGroupRequest command){
        shopService.deleteMenuGroup(command);
    }

    /**
     * 메뉴 삭제
     *
     * @param command
     */
    public void deleteMenu(ShopCommand.MenuRequest command){
        shopService.deleteMenu(command);
    }

    /**
     * 메뉴 옵션 그룹 삭제
     *
     * @param command
     */
    public void deleteMenuOptionGroup(ShopCommand.MenuOptionGroupRequest command){
        shopService.deleteMenuOptionGroup(command);
    }

    /**
     * 메뉴 옵션 삭제
     *
     * @param command
     */
    public void deleteMenuOption(ShopCommand.MenuOptionRequest command){
        shopService.deleteMenuOption(command);
    }

    /**
     * 사장님 가게 단건 조회
     *
     * @param ownerToken
     * @return
     */
    public ShopInfo.Main retrieveShopInfo(String ownerToken){
        var shopInfo = shopService.retrieveShopInfo(ownerToken);
        return shopInfo;
    }
}
