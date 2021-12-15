package dev.toyproject.foodDelivery.shop.domain.shopBusiness;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "shop_business")
public class ShopBusiness extends AbstractEntity {

    @Id
    private String shopBusinessId;                 // 사업자등록번호

    private String shopBusinessTaxationType;       // 사업자과세구분
    private String shopBusinessStatus;             // 사업자 업태
    private String shopBusinessCategory;           // 사업자 종목
    private String shopBusinessName;               // 사업자 상호
    private String shopBusinessRepresentativeType; // 사업자 대표자구분
    private String shopBusinessRepresentativeName; // 사업자 대표자성명
    private String shopBusinessLocation;           // 사업장 소재지

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Builder
    public ShopBusiness(
            Shop
                    shop,
            String shopBusinessId,
            String shopBusinessTaxationType,
            String shopBusinessStatus,
            String shopBusinessCategory,
            String shopBusinessName,
            String shopBusinessRepresentativeType,
            String shopBusinessRepresentativeName,
            String shopBusinessLocation
    ){
        if(StringUtils.isEmpty(shopBusinessId))                 throw new InvalidParamException("business.shopBusinessId");
        if(StringUtils.isEmpty(shopBusinessTaxationType))       throw new InvalidParamException("business.shopBusinessTaxationType");
        if(StringUtils.isEmpty(shopBusinessStatus))             throw new InvalidParamException("business.shopBusinessStatus");
        if(StringUtils.isEmpty(shopBusinessCategory))           throw new InvalidParamException("business.shopBusinessCategory");
        if(StringUtils.isEmpty(shopBusinessName))               throw new InvalidParamException("business.ShopBusinessName");
        if(StringUtils.isEmpty(shopBusinessRepresentativeType)) throw new InvalidParamException("business.shopBusinessRepresentativeType");
        if(StringUtils.isEmpty(shopBusinessRepresentativeName)) throw new InvalidParamException("business.shopBusinessRepresentativeName");
        if(StringUtils.isEmpty(shopBusinessLocation))           throw new InvalidParamException("business.shopBusinessLocation");
        if(shop == null)                                        throw new InvalidParamException("business.shop");

        this.shop                           = shop;
        this.shopBusinessId                 = shopBusinessId;
        this.shopBusinessTaxationType       = shopBusinessTaxationType;
        this.shopBusinessStatus             = shopBusinessStatus;
        this.shopBusinessCategory           = shopBusinessCategory;
        this.shopBusinessName               = shopBusinessName;
        this.shopBusinessRepresentativeType = shopBusinessRepresentativeType;
        this.shopBusinessRepresentativeName = shopBusinessRepresentativeName;
        this.shopBusinessLocation           = shopBusinessLocation;
    }
}
