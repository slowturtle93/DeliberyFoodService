package dev.toyproject.foodDelivery.shop.domain;

import java.util.List;

public interface ShopService {

    public String registerShop(ShopCommand.ShopRequest command);

    public void disableShop(String shopToken);

    public ShopInfo.Main updateShop(String shopToken, ShopCommand.ShopRequest command);

    public String registerMenu(String shopToken, List<ShopCommand.MenuGroupRequest> command);

    public void updateMenu(List<ShopCommand.MenuGroupRequest> command);

    public void deleteMenuGroup(ShopCommand.MenuGroupRequest command);
}
