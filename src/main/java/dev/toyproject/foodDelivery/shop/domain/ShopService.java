package dev.toyproject.foodDelivery.shop.domain;

import java.util.List;

public interface ShopService {

    public String registerShop(ShopCommand.ShopRequest command);

    public void disableShop(String shopToken);

    public ShopInfo.Main updateShop(String shopToken, ShopCommand.ShopRequest command);

    public String registerMenu(String shopToken, List<ShopCommand.MenuGroupRequest> command);

    public void updateMenu(List<ShopCommand.MenuGroupRequest> command);

    public void deleteMenuGroup(ShopCommand.MenuGroupRequest command);

    public void deleteMenu(ShopCommand.MenuRequest command);

    public void deleteMenuOptionGroup(ShopCommand.MenuOptionGroupRequest command);

    public void deleteMenuOption(ShopCommand.MenuOptionRequest command);

    public ShopInfo.Main retrieveShopInfo(String shopToken);

    public List<ShopInfo.ShopMain> searchShop(ShopCommand.MemberLocationRequest request);

    public List<ShopInfo.ShopOrderList> retrieveShopOrderMenu(ShopCommand.ShopOrderMenuRequest command);
}
