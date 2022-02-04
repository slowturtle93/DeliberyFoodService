package dev.toyproject.foodDelivery.shop.application;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.member.application.MemberFacade;
import dev.toyproject.foodDelivery.member.domain.MemberReader;
import dev.toyproject.foodDelivery.notification.fcm.domain.FcmNotificationRequest;
import dev.toyproject.foodDelivery.notification.fcm.domain.FcmService;
import dev.toyproject.foodDelivery.notification.fcm.infrastructrue.FcmNotificationInfo;
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
    private final FcmService fcmService;
    private final RedisCacheUtil redisCacheUtil;

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

    /**
     * 특정 좌표 위치 기반으로 반경 2km 이내의 가게 조회
     * @param request
     * @return
     */
    public List<ShopInfo.ShopMain> searchShop(ShopCommand.MemberLocationRequest request){
        return shopService.searchShop(request);
    }

    /**
     * 가게 주문 list 조회
     *
     * @param command
     * @return
     */
    public List<ShopInfo.ShopOrderList> retrieveShopOrderMenu(ShopCommand.ShopOrderMenuRequest command){
        return shopService.retrieveShopOrderMenu(command);
    }

    /**
     * 특정 주문 주문 승인 상태 처리
     *
     * @param command
     */
    public void shopOrderApproval(ShopCommand.ShopOrderConfirmRequest command){
        shopService.shopOrderApproval(command.getOrderToken());
        var memberDeviceToken = redisCacheUtil.getDeviceTokenInfo(command.getMemberToken());
        fcmService.sendFcm(new FcmNotificationRequest(FcmNotificationInfo.FCM_OWNER_ORDER_APPROVAL_TITLE, FcmNotificationInfo.FCM_OWNER_ORDER_APPROVAL_MESSAGE, memberDeviceToken));
    }
}
