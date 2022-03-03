package dev.toyproject.foodDelivery.shop.infrastructure.shopAddress;

import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAddressRepository extends JpaRepository<ShopAddress, Long> {
}
