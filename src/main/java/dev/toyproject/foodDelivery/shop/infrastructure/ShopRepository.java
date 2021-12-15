package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
