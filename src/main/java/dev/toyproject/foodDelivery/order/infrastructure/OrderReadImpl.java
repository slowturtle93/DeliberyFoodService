package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReadImpl implements OrderRead {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
