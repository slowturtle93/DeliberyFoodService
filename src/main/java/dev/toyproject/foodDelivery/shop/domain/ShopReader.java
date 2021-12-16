package dev.toyproject.foodDelivery.shop.domain;

public interface ShopReader {

    public Shop getShopByToken(String shopToken);

    public ShopInfo.Main getShopSeries(Shop shop);
}
