package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderMenuSeriesFactory;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMenuSeriesFactoryImpl implements OrderMenuSeriesFactory {

    /**
     * 주문 메뉴 및 주문 메뉴 옵션 등록
     *
     * @param order
     * @param registerOrder
     * @return
     */
    @Override
    public List<OrderMenu> store(Order order, OrderCommand.RegisterOrder registerOrder) {
        return null;
    }
}
