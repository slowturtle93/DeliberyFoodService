package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;

import java.util.List;

public interface ShopMenuFactory {
    public List<MenuGroup> shopMenuStore(Shop shop, List<ShopCommand.MenuGroupRequest> command);

    public void updateMenu(List<ShopCommand.MenuGroupRequest> command);

    public void deleteMenuGroup(ShopCommand.MenuGroupRequest command);

    public void deleteMenu(ShopCommand.MenuRequest command);

    public void deleteMenuOptionGroup(ShopCommand.MenuOptionGroupRequest command);
}
