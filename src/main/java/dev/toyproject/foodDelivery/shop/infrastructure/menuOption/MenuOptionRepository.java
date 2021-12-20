package dev.toyproject.foodDelivery.shop.infrastructure.menuOption;

import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
