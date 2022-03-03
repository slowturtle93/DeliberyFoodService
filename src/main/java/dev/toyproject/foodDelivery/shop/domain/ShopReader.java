package dev.toyproject.foodDelivery.shop.domain;

import java.util.List;

public interface ShopReader {

    public Shop getShopByToken(String shopToken);

    public ShopInfo.Main getShopSeries(Shop shop);

    public List<Shop> searchShop(ShopCommand.MemberLocationRequest request);

    public List<ShopInfo.ShopMain> getShopListSeries(List<Shop> shopList);
}
