package dev.toyproject.foodDelivery.order.application;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.common.util.redis.RedisKeyFactory;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderFactory;
import dev.toyproject.foodDelivery.order.domain.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderFactory orderFactory;
    private final RedisCacheUtil redisCacheUtil;
    private final OrderService orderService;

    /**
     * 장바구니 메뉴 등록
     *
     * @param command
     */
    public List<OrderCommand.OrderBasketRequest> registerMenuBasket(OrderCommand.OrderBasketRequest command){
        return orderService.registerMenuBasket(command);
    }
}
