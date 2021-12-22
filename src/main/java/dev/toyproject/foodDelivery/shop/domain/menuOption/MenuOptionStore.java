package dev.toyproject.foodDelivery.shop.domain.menuOption;

public interface MenuOptionStore {
    MenuOption store(MenuOption menuOption);

    void delete(MenuOption menuOption);
}
