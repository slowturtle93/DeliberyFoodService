package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

    @Getter
    @Builder
    @ToString
    public static class MenuGroupRequest{
        private Long id;
        private String menuGroupName;
        private Long ordering;
        private List<MenuRequest> menuList;

        public MenuGroup toEntity(Shop shop){
            return MenuGroup.builder()
                    .shop(shop)
                    .id(id)
                    .menuGroupName(menuGroupName)
                    .ordering(ordering)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class MenuRequest{
        private Long id;
        private String menuName;
        private String menuPriceName;
        private Long menuPrice;
        private String menuPhoto;
        private String content;
        private Long ordering;
        private List<MenuOptionGroupRequest> menuOptionGroupList;

        public Menu toEntity(MenuGroup menuGroup){
            return Menu.builder()
                    .menuGroup(menuGroup)
                    .id(id)
                    .menuName(menuName)
                    .menuPriceName(menuPriceName)
                    .menuPrice(menuPrice)
                    .menuPhoto(menuPhoto)
                    .content(content)
                    .ordering(ordering)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class MenuOptionGroupRequest{
        private Long id;
        private String menuOptionGroupName;
        private Long ordering;
        private List<MenuOptionRequest> menuOptionList;

        public MenuOptionGroup toEntity(Menu menu){
            return MenuOptionGroup.builder()
                    .menu(menu)
                    .id(id)
                    .menuOptionGroupName(menuOptionGroupName)
                    .ordering(ordering)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class MenuOptionRequest{
        private Long id;
        private String menuOptionName;
        private Long menuOptionPrice;
        private Long ordering;

        public MenuOption toEntity(MenuOptionGroup menuOptionGroup){
            return MenuOption.builder()
                    .menuOptionGroup(menuOptionGroup)
                    .id(id)
                    .menuOptionName(menuOptionName)
                    .menuOptionPrice(menuOptionPrice)
                    .ordering(ordering)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class MemberLocationRequest{
        private final Double x;
        private final Double y;
    }

    @Getter
    @Builder
    @ToString
    public static class ShopOrderMenuRequest{
        private String ownerToken;
        private String shopToken;
    }

    @Getter
    @Builder
    @ToString
    public static class ShopOrderConfirmRequest{
        private String orderToken;
        private String memberToken;
    }
}
