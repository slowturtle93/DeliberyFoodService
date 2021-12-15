package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;

public interface ShopFactory {

    public ShopBusiness shopBusinessStore(Shop shop, ShopCommand.ShopBusinessRequest command);

    public ShopAddress shopAddressStore(Shop shop, ShopCommand.ShopAddressRequest command);
}
