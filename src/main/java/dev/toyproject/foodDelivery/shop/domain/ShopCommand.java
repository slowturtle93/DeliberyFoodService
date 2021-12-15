package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ShopCommand {

    @Getter
    @Builder
    @ToString
    public static class ShopRequest{
        private String ownerToken;         // 사장님 토큰 정보
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
        private ShopBusinessRequest shopBusinessInfoRequest; // 사업자 정보
        private ShopAddressRequest shopAddressInfoRequest;   // 가게 주소

        public Shop toEntity(){
            return Shop.builder()
                    .ownerToken(ownerToken)
                    .shopBuildingInfoId(shopBuildingInfoId)
                    .shopCategoryId(shopCategoryId)
                    .shopNm(shopNm)
                    .shopDeliveryRegion(shopDeliveryRegion)
                    .shopTel(shopTel)
                    .shopInfo(shopInfo)
                    .shopMinOrdPrice(shopMinOrdPrice)
                    .shopNotice(shopNotice)
                    .shopOperatingTime(shopOperatingTime)
                    .shopClosedDate(shopClosedDate)
                    .shopOrderType(shopOrderType)
                    .shopOriginInfo(shopOriginInfo)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ShopBusinessRequest{
        private String shopBusinessId;
        private String shopBusinessTaxationType;
        private String shopBusinessStatus;
        private String shopBusinessCategory;
        private String shopBusinessName;
        private String shopBusinessRepresentativeType;
        private String shopBusinessRepresentativeName;
        private String shopBusinessLocation;

        public ShopBusiness toEntity(Shop shop){
            return ShopBusiness.builder()
                    .shop(shop)
                    .shopBusinessId(shopBusinessId)
                    .shopBusinessTaxationType(shopBusinessTaxationType)
                    .shopBusinessStatus(shopBusinessStatus)
                    .shopBusinessCategory(shopBusinessCategory)
                    .shopBusinessName(shopBusinessName)
                    .shopBusinessRepresentativeType(shopBusinessRepresentativeType)
                    .shopBusinessRepresentativeName(shopBusinessRepresentativeName)
                    .shopBusinessLocation(shopBusinessLocation)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ShopAddressRequest{
        private AddressFragment addressFragment; // 가게 주소
        private String detail;                   // 가게 상세 주소

        public ShopAddress toEntity(Shop shop){
            return ShopAddress.builder()
                    .shop(shop)
                    .detail(detail)
                    .addressFragment(addressFragment)
                    .build();
        }
    }
}
