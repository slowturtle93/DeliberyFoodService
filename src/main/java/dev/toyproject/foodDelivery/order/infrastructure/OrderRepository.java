package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
