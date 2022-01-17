package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderMenuSeriesFactory;
import dev.toyproject.foodDelivery.order.domain.OrderStore;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMenuSeriesFactoryImpl implements OrderMenuSeriesFactory {

    private final OrderStore orderStore;

    /**
     * 주문 메뉴 및 주문 메뉴 옵션 등록
     *
     * @param order
     * @param registerOrder
     * @return
     */
    @Override
    public List<OrderMenu> store(Order order, OrderCommand.RegisterOrder registerOrder) {
        return registerOrder.getOrderMenuList().stream()
                .map(orderMenuRequest -> {
                    //  주문 메뉴 등록
                    var initOrderMenu = orderMenuRequest.toEntity(order);
                    var orderMenu = orderStore.store(initOrderMenu);

                    orderMenuRequest.getOrderMenuOptionGroupList().forEach(orderMenuOptionGroupRequest -> {

                        // 주문 메뉴 옵션 그룹 등록
                        var initOrderMenuOptionGroup = orderMenuOptionGroupRequest.toEntity(orderMenu);
                        var orderMenuOptionGroup = orderStore.store(initOrderMenuOptionGroup);

                        orderMenuOptionGroupRequest.getOrderMenuOptionList().forEach(orderMenuOptionRequest -> {

                            // 주문 메뉴 옵션 등록
                            var initOrderMenuOption = orderMenuOptionRequest.toEntity(orderMenuOptionGroup);
                            orderStore.store(initOrderMenuOption);

                        });
                    });
                    return orderMenu;
                }).collect(Collectors.toList());
    }
}
