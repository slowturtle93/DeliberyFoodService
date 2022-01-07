package dev.toyproject.foodDelivery.order.application;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.common.util.redis.RedisKeyFactory;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderFactory;
import dev.toyproject.foodDelivery.order.domain.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderFactory orderFactory;
    private final RedisCacheUtil redisCacheUtil;

    /**
     * 장바구니 메뉴 등록
     *
     * @param command
     */
    public void registerMenuBasket(OrderCommand.OrderBasketRequest command){
        String hashKey = orderFactory.makeHashKey(command);
        redisCacheUtil.setRedisCacheMenuBasket(command.getMemberToken(), RedisKeyFactory.MENU_SHOPPING_BASKET, hashKey, command);
    }
}
