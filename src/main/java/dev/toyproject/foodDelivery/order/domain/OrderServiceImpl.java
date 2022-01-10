package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.common.util.redis.RedisKeyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final RedisCacheUtil redisCacheUtil;
    private final OrderFactory orderFactory;

    /**
     * 장바구니 메뉴 등록
     *
     * @param command
     */
    @Override
    public List<OrderCommand.OrderBasketRequest> registerMenuBasket(OrderCommand.OrderBasketRequest command) {
        String hashKey = orderFactory.makeHashKey(command);
        orderFactory.orderBasketShopCheck(command);                                                                               // 같은 가게 확인
        orderFactory.duplicationMenu(command,hashKey);                                                                            // 메뉴 & 옵션 동일 여부 확인
        redisCacheUtil.setRedisCacheMenuBasket(command.getMemberToken(), RedisKeyFactory.MENU_SHOPPING_BASKET, hashKey, command); // Redis 등록
        return redisCacheUtil.getMenuBasketList(command.getMemberToken(), RedisKeyFactory.MENU_SHOPPING_BASKET);                  // 등록된 장바구니 메뉴 조회
    }
}
