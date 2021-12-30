package dev.toyproject.foodDelivery.shop.domain;

import com.google.common.collect.Lists;
import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "shop")
public class Shop extends AbstracEntity {

    private final static String SHOP_PREFIX = "shp_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                   // 가게 시퀀스

    private String ownerToken;         // 사장님 토큰 정보
    private String shopToken;          // 가게 토큰 정보
    private String shopBuildingInfoId; // 가게 건물 정보
    private String shopCategoryId;     // 가게 카테고리 정보
    private String shopNm;             // 가게명
    private String shopDeliveryRegion; // 가게 배달 지역
    private String shopTel;            // 가게 전화번호
    private String shopInfo;           // 가게 소개
    private Long   shopMinOrdPrice;    // 최소 주문금액
    private String shopNotice;         // 가게 공지사항
    private String shopOperatingTime;  // 가게 운영시간
    private String shopClosedDate;     // 가게 휴무일
    private String shopOrderType;      // 주문타입
    private String shopOriginInfo;     // 원산지 정보

    @Enumerated(EnumType.STRING)
    private Shop.Status status;        // 상태

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.PERSIST)
    private ShopBusiness shopBusiness; //사업자 정보

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.PERSIST)
    private ShopAddress shopAddress;   // 가게 주소

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<MenuGroup> menuGroupList = Lists.newArrayList();

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Shop(
            String ownerToken,
            String shopBuildingInfoId,
            String shopCategoryId,
            String shopNm,
            String shopDeliveryRegion,
            String shopTel,
            String shopInfo,
            Long   shopMinOrdPrice,
            String shopNotice,
            String shopOperatingTime,
            String shopClosedDate,
            String shopOrderType,
            String shopOriginInfo
    ){
        if(StringUtils.isEmpty(shopNm))             throw new InvalidParamException("shop.shopNm");
        if(StringUtils.isEmpty(shopDeliveryRegion)) throw new InvalidParamException("shop.shopDeliveryRegion");
        if(StringUtils.isEmpty(shopTel))            throw new InvalidParamException("shop.shopTel");
        if(StringUtils.isEmpty(shopInfo))           throw new InvalidParamException("shop.shopInfo");
        if(StringUtils.isEmpty(shopNotice))         throw new InvalidParamException("shop.shopNotice");
        if(StringUtils.isEmpty(shopOperatingTime))  throw new InvalidParamException("shop.shopOperatingTime");
        if(StringUtils.isEmpty(shopClosedDate))     throw new InvalidParamException("shop.shopClosedDate");
        if(StringUtils.isEmpty(shopOrderType))      throw new InvalidParamException("shop.shopOrderType");
        if(StringUtils.isEmpty(shopOriginInfo))     throw new InvalidParamException("shop.shopOriginInfo");
        if(StringUtils.isEmpty(shopCategoryId))     throw new InvalidParamException("shop.shopCategoryId");
        if(StringUtils.isEmpty(shopBuildingInfoId)) throw new InvalidParamException("shop.shopBuildingInfoId");
        if(StringUtils.isEmpty(ownerToken))         throw new InvalidParamException("shop.ownerToken");
        if(shopMinOrdPrice == null)                 throw new InvalidParamException("shop.shopMinOrdPrice");

        this.ownerToken         = ownerToken;
        this.shopToken          = TokenGenerator.randomCharacterWithPrefix(SHOP_PREFIX);
        this.shopNm             = shopNm;
        this.shopDeliveryRegion = shopDeliveryRegion;
        this.shopTel            = shopTel;
        this.shopInfo           = shopInfo;
        this.shopNotice         = shopNotice;
        this.shopOperatingTime  = shopOperatingTime;
        this.shopClosedDate     = shopClosedDate;
        this.shopOrderType      = shopOrderType;
        this.shopOriginInfo     = shopOriginInfo;
        this.shopCategoryId     = shopCategoryId;
        this.shopBuildingInfoId = shopBuildingInfoId;
        this.shopMinOrdPrice    = shopMinOrdPrice;
        this.status             = Shop.Status.ENABLE;
    }

    // 가게 상태 [ENABLE] 변경
    public void enable() { this.status = Shop.Status.ENABLE; }

    // 가게 상태 [DISABLE] 변경
    public void disable() { this.status = Shop.Status.DISABLE; }

    public void updateShopInfo(ShopCommand.ShopRequest command){

        if(StringUtils.isEmpty(command.getShopBuildingInfoId())) throw new InvalidParamException("shop.shopBuildingInfoId");
        if(StringUtils.isEmpty(command.getShopCategoryId()))     throw new InvalidParamException("shop.shopCategoryId");
        if(StringUtils.isEmpty(command.getShopNm()))             throw new InvalidParamException("shop.shopNm");
        if(StringUtils.isEmpty(command.getShopDeliveryRegion())) throw new InvalidParamException("shop.shopDeliveryRegion");
        if(StringUtils.isEmpty(command.getShopTel()))            throw new InvalidParamException("shop.shopTel");
        if(StringUtils.isEmpty(command.getShopInfo()))           throw new InvalidParamException("shop.shopInfo");
        if(StringUtils.isEmpty(command.getShopNotice()))         throw new InvalidParamException("shop.shopNotice");
        if(StringUtils.isEmpty(command.getShopOperatingTime()))  throw new InvalidParamException("shop.shopOperatingTime");
        if(StringUtils.isEmpty(command.getShopClosedDate()))     throw new InvalidParamException("shop.shopClosedDate");
        if(StringUtils.isEmpty(command.getShopOrderType()))      throw new InvalidParamException("shop.shopClosedDate");
        if(StringUtils.isEmpty(command.getShopOriginInfo()))     throw new InvalidParamException("shop.shopOrderType");
        if(command.getShopMinOrdPrice() == null)                 throw new InvalidParamException("shop.shopMinOrdPrice");

        this.shopBuildingInfoId = command.getShopBuildingInfoId(); // 가게 건물 정보
        this.shopCategoryId     = command.getShopCategoryId();     // 가게 카테고리 정보
        this.shopNm             = command.getShopNm();             // 가게명
        this.shopDeliveryRegion = command.getShopDeliveryRegion(); // 가게 배달 지역
        this.shopTel            = command.getShopTel();            // 가게 전화번호
        this.shopInfo           = command.getShopInfo();           // 가게 소개
        this.shopMinOrdPrice    = command.getShopMinOrdPrice();    // 최소 주문금액
        this.shopNotice         = command.getShopNotice();         // 가게 공지사항
        this.shopOperatingTime  = command.getShopOperatingTime();  // 가게 운영시간
        this.shopClosedDate     = command.getShopClosedDate();     // 가게 휴무일
        this.shopOrderType      = command.getShopOrderType();      // 주문타입
        this.shopOriginInfo     = command.getShopOriginInfo();     // 원산지 정보
    }
}
