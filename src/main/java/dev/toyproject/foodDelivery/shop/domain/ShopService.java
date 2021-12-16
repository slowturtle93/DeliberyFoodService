package dev.toyproject.foodDelivery.shop.domain;

public interface ShopService {

    public String registerShop(ShopCommand.ShopRequest command);

    public void disableShop(String shopToken);

    public ShopInfo.Main updateShop(String shopToken, ShopCommand.ShopRequest command);
}
