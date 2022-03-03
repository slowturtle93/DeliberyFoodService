package dev.toyproject.foodDelivery.shop.infrastructure.shopBusiness;

import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopBusinessRepository extends JpaRepository<ShopBusiness, String> {
}
