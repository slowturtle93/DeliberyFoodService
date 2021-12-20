package dev.toyproject.foodDelivery.shop.infrastructure.menuGroup;

import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {
}
