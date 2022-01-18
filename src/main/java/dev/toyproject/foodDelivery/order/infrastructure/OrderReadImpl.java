package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReadImpl implements OrderRead {

    private final OrderRepository orderRepository;

    /**
     * 주문 정보 단건 조회
     *
     * @param orderToken
     * @return
     */
    @Override
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 주문 정보 다건 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<Order> getOrderList(String memberToken) {
        return orderRepository.findByMemberToken(memberToken);
    }
}
