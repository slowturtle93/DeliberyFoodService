package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuOptionGroupRepository extends JpaRepository<OrderMenuOptionGroup, Long> {
}
