package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.OrderStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;

    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }
}
