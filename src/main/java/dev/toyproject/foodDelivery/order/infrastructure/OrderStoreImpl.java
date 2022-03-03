package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderStore;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOption;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOptionGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderMenuOptionGroupRepository orderMenuOptionGroupRepository;
    private final OrderMenuOptionRepository orderMenuOptionRepository;

    /**
     * 주문 정보 등록
     *
     * @param order
     * @return
     */
    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }

    /**
     * 주문 메뉴 정보 등록
     *
     * @param orderMenu
     * @return
     */
    @Override
    public OrderMenu store(OrderMenu orderMenu) {
        return orderMenuRepository.save(orderMenu);
    }

    /**
     * 주문 메뉴 옵션 그룹 정보 등록
     *
     * @param orderMenuOptionGroup
     * @return
     */
    @Override
    public OrderMenuOptionGroup store(OrderMenuOptionGroup orderMenuOptionGroup) {
        return orderMenuOptionGroupRepository.save(orderMenuOptionGroup);
    }

    /**
     * 주문 메뉴 옵션 정보 등록
     *
     * @param orderMenuOption
     * @return
     */
    @Override
    public OrderMenuOption store(OrderMenuOption orderMenuOption) {
        return orderMenuOptionRepository.save(orderMenuOption);
    }
}
