package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import lombok.Getter;
import lombok.ToString;

public class ShopInfo {

    @Getter
    @ToString
    public static class Main{
        private final String ownerToken;         // 사장님 정보
        private final String shopToken;          // 가게 토큰
        private final String shopBuildingInfoId; // 가게 건물 정보
        private final String shopCategoryId;     // 가게 카테고리 정보
        private final String shopNm;             // 가게명
        private final String shopDeliveryRegion; // 가게 배달 지역
        private final String shopTel;            // 가게 전화번호
        private final String shopInfo;           // 가게 소개
        private final Long   shopMinOrdPrice;    // 최소 주문금액
        private final String shopNotice;         // 가게 공지사항
        private final String shopOperatingTime;  // 가게 운영시간
        private final String shopClosedDate;     // 가게 휴무일
        private final String shopOrderType;      // 주문타입
        private final String shopOriginInfo;     // 원산지 정보
        private final Shop.Status status;        // 상태
        private final ShopBusinessInfo shopBusiness;     // 사업자 정보
        private final ShopAddressInfo shopAddress;       // 가게 주소 정보

        public Main(Shop shop, ShopBusinessInfo shopBusinessInfo, ShopAddressInfo shopAddressInfo){
            this.ownerToken         = shop.getOwnerToken();
            this.shopToken          = shop.getShopToken();
            this.shopBuildingInfoId = shop.getShopBuildingInfoId();
            this.shopCategoryId     = shop.getShopCategoryId();
            this.shopNm             = shop.getShopNm();
            this.shopDeliveryRegion = shop.getShopDeliveryRegion();
            this.shopTel            = shop.getShopTel();
            this.shopInfo           = shop.getShopInfo();
            this.shopMinOrdPrice    = shop.getShopMinOrdPrice();
            this.shopNotice         = shop.getShopNotice();
            this.shopOperatingTime  = shop.getShopOperatingTime();
            this.shopClosedDate     = shop.getShopClosedDate();
            this.shopOrderType      = shop.getShopOrderType();
            this.shopOriginInfo     = shop.getShopOriginInfo();
            this.status             = shop.getStatus();
            this.shopBusiness       = shopBusinessInfo;
            this.shopAddress        = shopAddressInfo;
        }
    }

    @Getter
    @ToString
    public static class ShopBusinessInfo{
        private final String shopBusinessId;                 // 사업자등록번호
        private final String shopBusinessTaxationType;       // 사업자과세구분
        private final String shopBusinessStatus;             // 사업자 업태
        private final String shopBusinessCategory;           // 사업자 종목
        private final String shopBusinessName;               // 사업자 상호
        private final String shopBusinessRepresentativeType; // 사업자 대표자구분
        private final String shopBusinessRepresentativeName; // 사업자 대표자성명
        private final String shopBusinessLocation;           // 사업장 소재지

        public ShopBusinessInfo(ShopBusiness shopBusiness){
            this.shopBusinessId                 = shopBusiness.getShopBusinessId();
            this.shopBusinessTaxationType       = shopBusiness.getShopBusinessTaxationType();
            this.shopBusinessStatus             = shopBusiness.getShopBusinessStatus();
            this.shopBusinessCategory           = shopBusiness.getShopBusinessCategory();
            this.shopBusinessName               = shopBusiness.getShopBusinessName();
            this.shopBusinessRepresentativeType = shopBusiness.getShopBusinessRepresentativeType();
            this.shopBusinessRepresentativeName = shopBusiness.getShopBusinessRepresentativeName();
            this.shopBusinessLocation           = shopBusiness.getShopBusinessLocation();
        }
    }

    @Getter
    @ToString
    public static class ShopAddressInfo{
        private final AddressFragment addressFragment; // 가게 주소
        private final String detail;                   // 가게 상세 주소

        public ShopAddressInfo(ShopAddress shopAddress){
            this.addressFragment = shopAddress.getAddressFragment();
            this.detail          = shopAddress.getDetail();
        }
    }
}
