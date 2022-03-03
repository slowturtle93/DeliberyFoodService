package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderToken(String orderToken);

    List<Order> findByMemberToken(String memberToken);

    List<Order> findByShopTokenAndStatus(String shopToken, Order.Status status);
}
