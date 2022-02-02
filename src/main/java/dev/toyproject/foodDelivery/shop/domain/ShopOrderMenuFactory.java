package dev.toyproject.foodDelivery.shop.domain;

import java.util.List;

public interface ShopOrderMenuFactory {
    public List<ShopInfo.ShopOrderList> retrieveOrderMenuList(ShopCommand.ShopOrderMenuRequest command);
}
