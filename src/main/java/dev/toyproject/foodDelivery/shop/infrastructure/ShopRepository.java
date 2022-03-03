package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByShopTokenAndStatus(String shopToken, Shop.Status status);
}
