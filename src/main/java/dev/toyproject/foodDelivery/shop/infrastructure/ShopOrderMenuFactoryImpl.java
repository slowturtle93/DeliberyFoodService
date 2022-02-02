package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.order.domain.OrderRead;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopInfo;
import dev.toyproject.foodDelivery.shop.domain.ShopOrderMenuFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopOrderMenuFactoryImpl implements ShopOrderMenuFactory {

    private final OrderRead orderRead;

    /**
     * 주문 메뉴 list 조회
     *
     * @param command
     */
    @Override
    public List<ShopInfo.ShopOrderList> retrieveOrderMenuList(ShopCommand.ShopOrderMenuRequest command) {

        var orderList = orderRead.getOrderByShopToken(command.getShopToken());

        return orderList.stream()
                .map(order ->{
                    var orderMenuList = order.getOrderMenuList();

                    var orderMenuInfoList = orderMenuList.stream()
                            .map(orderMenu ->{

                                var orderMenuOptionGroupList = orderMenu.getOrderMenuOptionGroupList();

                                var orderMenuOptionGroupInfoList = orderMenuOptionGroupList.stream()
                                        .map(orderMenuOptionGroup -> {
                                            var orderMenuOptionList = orderMenuOptionGroup.getOrderMenuOptionList();
                                            var orderMenuOptionInfoList = orderMenuOptionList.stream()
                                                    .map(ShopInfo.ShopOrderMenuOption::new)
                                                    .collect(Collectors.toList());

                                          return new ShopInfo.ShopOrderMenuOptionGroup(orderMenuOptionGroup, orderMenuOptionInfoList);
                                        }).collect(Collectors.toList());

                                    return new ShopInfo.ShopOrderMenu(orderMenu, orderMenuOptionGroupInfoList);
                            }).collect(Collectors.toList());

                    return new ShopInfo.ShopOrderList(order, orderMenuInfoList);
                }).collect(Collectors.toList());

    }
}
