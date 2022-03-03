package dev.toyproject.foodDelivery.shop.domain.menuOptionGroup;

public interface MenuOptionGroupStore {
    MenuOptionGroup store(MenuOptionGroup menuOptionGroup);

    void delete(MenuOptionGroup menuOptionGroup);
}
