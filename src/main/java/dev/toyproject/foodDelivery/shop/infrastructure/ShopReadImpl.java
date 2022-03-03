package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopInfo;
import dev.toyproject.foodDelivery.shop.domain.ShopReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopReadImpl implements ShopReader {

    private final ShopRepository shopRepository;
    private final ShopRepositorySupportImpl shopRepositorySupport;

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

        var shopMenuGroupList = shop.getMenuGroupList();
        var menuGroupInfoList = shopMenuGroupList.stream()
                .map(shopMenuGroup -> {

                    // menuInfo list
                    var menuList = shopMenuGroup.getMenuList();
                    var menuInfoList = menuList.stream()
                            .map(menu -> {

                                // menu Option Group List
                                var menuOptionGroupList = menu.getMenuOptionGroupList();
                                var menuOptionGroupInfoList = menuOptionGroupList.stream()
                                        .map(menuOptionGroup ->{

                                            // menu Option List
                                            var menuOptionList = menuOptionGroup.getMenuOptionList();
                                            var menuOptionInfoList = menuOptionList.stream()
                                                    .map(ShopInfo.MenuOptionInfo::new)
                                                    .collect(Collectors.toList());

                                            return new ShopInfo.MenuOptionGroupInfo(menuOptionGroup, menuOptionInfoList);
                                        }).collect(Collectors.toList());

                                return new ShopInfo.MenuInfo(menu, menuOptionGroupInfoList);
                            }).collect(Collectors.toList());

                    return new ShopInfo.MenuGroupInfo(shopMenuGroup, menuInfoList);
                }).collect(Collectors.toList());

        return new ShopInfo.Main(shop, shopBusinessInfo, shopAddressInfo, menuGroupInfoList);
    }

    /**
     * nativeQuery 를 통해 특정 좌표 위치 기반으로 반경 2km 이내의 가게 조회
     *
     * @param request
     * @return
     */
    @Override
    public List<Shop> searchShop(ShopCommand.MemberLocationRequest request) {
        var shopList = shopRepositorySupport.findSearchShop(request);
        return shopList;
    }

    /**
     * 가게 정보 List Series
     *
     * @param shopList
     * @return
     */
    @Override
    public List<ShopInfo.ShopMain> getShopListSeries(List<Shop> shopList) {

        var shopInfoList = shopList;

        return shopInfoList.stream()
                .map(ShopInfo.ShopMain::new)
                .collect(Collectors.toList());
    }
}
