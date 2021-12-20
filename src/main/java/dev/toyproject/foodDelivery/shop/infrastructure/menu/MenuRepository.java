package dev.toyproject.foodDelivery.shop.infrastructure.menu;

import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
