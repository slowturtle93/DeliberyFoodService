package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopStoreImpl implements ShopStore {

    private final ShopRepository shopRepository;

    /**
     * 사장님 가게 등록
     *
     * @param initShop
     * @return
     */
    @Override
    public Shop store(Shop initShop) {

        if(StringUtils.isEmpty(initShop.getOwnerToken()))         throw new InvalidParamException("shop.getOwnerToken()");
        if(StringUtils.isEmpty(initShop.getShopToken()))          throw new InvalidParamException("shop.getShopToken()");
        if(StringUtils.isEmpty(initShop.getShopBuildingInfoId())) throw new InvalidParamException("shop.getShopBuildingInfoId()");
        if(StringUtils.isEmpty(initShop.getShopCategoryId()))     throw new InvalidParamException("shop.getShopCategoryId()");
        if(StringUtils.isEmpty(initShop.getShopNm()))             throw new InvalidParamException("shop.getShopNm()");
        if(StringUtils.isEmpty(initShop.getShopDeliveryRegion())) throw new InvalidParamException("shop.getShopDeliveryRegion()");
        if(StringUtils.isEmpty(initShop.getShopTel()))            throw new InvalidParamException("shop.getShopTel()");
        if(StringUtils.isEmpty(initShop.getShopInfo()))           throw new InvalidParamException("shop.getShopInfo()");
        if(StringUtils.isEmpty(initShop.getShopNotice()))         throw new InvalidParamException("shop.getShopNotice()");
        if(StringUtils.isEmpty(initShop.getShopOperatingTime()))  throw new InvalidParamException("shop.getShopOperatingTime()");
        if(StringUtils.isEmpty(initShop.getShopClosedDate()))     throw new InvalidParamException("shop.getShopClosedDate()");
        if(StringUtils.isEmpty(initShop.getShopOrderType()))      throw new InvalidParamException("shop.getShopOrderType()");
        if(StringUtils.isEmpty(initShop.getShopOriginInfo()))     throw new InvalidParamException("shop.getShopOriginInfo()");
        if(initShop.getShopMinOrdPrice() == null)                 throw new InvalidParamException("shop.getShopMinOrdPrice()");
        if(initShop.getStatus() == null)                          throw new InvalidParamException("shop.getStatus()");

        return shopRepository.save(initShop);
    }
}