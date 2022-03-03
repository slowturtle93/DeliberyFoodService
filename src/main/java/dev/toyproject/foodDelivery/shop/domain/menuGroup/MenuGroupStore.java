package dev.toyproject.foodDelivery.shop.domain.menuGroup;

public interface MenuGroupStore {
    MenuGroup store(MenuGroup menuGroup);

    void delete(MenuGroup menuGroup);
}
