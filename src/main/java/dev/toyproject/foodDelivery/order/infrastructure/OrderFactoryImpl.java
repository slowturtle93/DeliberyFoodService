package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

}
