package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        orderFactory.registerCacheMenuBasket(command.getMemberToken(), hashKey, command);   // Redis 등록
        return orderFactory.retrieveMenuBasket(command.getMemberToken());                   // 등록된 장바구니 메뉴 조회
    }

    /**
     * 장바구니 특정 메뉴 삭제
     *
     * @param command
     */
    @Override
    public List<OrderCommand.OrderBasketRequest> deleteMenuBasket(OrderCommand.OrderBasketRequest command) {
        String hashKey = orderFactory.makeHashKey(command);
        orderFactory.removeMenuBasket(command.getMemberToken(), hashKey);
        return orderFactory.retrieveMenuBasket(command.getMemberToken());
    }

    /**
     * Redis 장바구니 메뉴 전체 삭제
     *
     * @param memberToken
     */
    @Override
    public void deleteMenuBasketAll(String memberToken) {
        redisCacheUtil.removeMenuBasketAll(memberToken);
    }

    /**
     * Redis 장바구니 메뉴 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<OrderCommand.OrderBasketRequest> retrieveMenuBasket(String memberToken) {
        return orderFactory.retrieveMenuBasket(memberToken);
    }
}
