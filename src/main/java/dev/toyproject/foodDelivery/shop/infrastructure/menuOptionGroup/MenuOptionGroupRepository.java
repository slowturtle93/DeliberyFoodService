package dev.toyproject.foodDelivery.shop.infrastructure.menuOptionGroup;

import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long> {
}
