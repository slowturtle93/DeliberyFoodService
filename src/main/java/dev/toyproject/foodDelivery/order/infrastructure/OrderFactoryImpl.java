package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderFactory;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFactoryImpl implements OrderFactory {

    private final RedisCacheUtil redisCacheUtil;

    /**
     * 장바구니 hashKey 생성
     * ( 메뉴 ID + 메뉴 옵션 ID )
     *
     * @param command
     * @return
     */
    @Override
    public String makeHashKey(OrderCommand.OrderBasketRequest command) {
        var menuBasket = command.getOrderBasketMenu();

        var menuBasketId = menuBasket.getId();
        StringBuilder menuOptionBasketId = new StringBuilder();

        menuBasket.getOrderBasketMenuOptionList()
                .forEach(menuOptionBasketList -> {
                    menuOptionBasketId.append(menuOptionBasketList.getId());
                });

        return menuBasketId + menuOptionBasketId.toString();
    }

    /**
     * 메뉴 장바구니 적재 시 다른 가게인지 Check
     *
     * @param command
     */
    @Override
    public void orderBasketShopCheck(OrderCommand.OrderBasketRequest command) {
        List<OrderInfo.OrderBasketInfo> orderBasketList = redisCacheUtil.getMenuBasketList(command.getMemberToken());

        if (orderBasketList == null) { return; } // 해당 사용자가 장바구니 내역이 없을 경우 정상적인 flow 진행

        orderBasketList.forEach(orderBasket -> {
            if (!command.getShopToken().equals(orderBasket.getShopToken())){
                throw new InvalidParamException("another orderBasket.shopToken");
            }
        });
    }

    /**
     * 장바구니 같은 메뉴 & 옵션 확인
     *
     * @param command
     * @param hashKey
     * @return
     */
    @Override
    public OrderInfo.OrderBasketInfo duplicationMenu(OrderCommand.OrderBasketRequest command, String hashKey) {

        OrderInfo.OrderBasketInfo orderBasketInfo = redisCacheUtil.getMenuBasketHashKey(command.getMemberToken(), hashKey);

        // 동일한 메뉴, 메뉴 옵션이 존재할 경우
        if (orderBasketInfo != null){
            Long MenuOptionCount = orderBasketInfo.getOrderBasketMenu().getOrderMenuCount();
            orderBasketInfo.getOrderBasketMenu().setOrderMenuCount(MenuOptionCount + 1);
        }

        return orderBasketInfo;
    }

    /**
     *
     * Redis 장바구니 메뉴 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken) {
        return redisCacheUtil.getMenuBasketList(memberToken);
    }

    /**
     * Redis 장바구니 메뉴 등록
     * @param memberToken
     * @param hashKey
     * @param command
     */
    @Override
    public void registerCacheMenuBasket(String memberToken, String hashKey, OrderCommand.OrderBasketRequest command) {
        redisCacheUtil.setRedisCacheMenuBasket(memberToken, hashKey, command);
    }

    /**
     * Redis 특정 메뉴 삭제
     *
     * @param memberToken
     * @param hashKey
     */
    @Override
    public void removeMenuBasket(String memberToken, String hashKey) {
        redisCacheUtil.removeMenuBasket(memberToken, hashKey);
    }
}
