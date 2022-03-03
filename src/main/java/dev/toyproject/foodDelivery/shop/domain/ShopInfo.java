package dev.toyproject.foodDelivery.shop.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.order.domain.Order;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOption;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenuOptionGroup;
import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.ShopAddress;
import dev.toyproject.foodDelivery.shop.domain.shopBusiness.ShopBusiness;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

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
        private final String regDate;            // 등록일
        private final String modDate;            // 수정일
        private final ShopBusinessInfo shopBusiness;     // 사업자 정보
        private final ShopAddressInfo shopAddress;       // 가게 주소 정보
        private final List<MenuGroupInfo> menuGroupList; // 메뉴 정보

        public Main(Shop shop, ShopBusinessInfo shopBusinessInfo, ShopAddressInfo shopAddressInfo, List<MenuGroupInfo> menuGroupInfoList){
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
            this.regDate            = shop.getRegDate().toString();
            this.modDate            = shop.getModDate().toString();
            this.shopBusiness       = shopBusinessInfo;
            this.shopAddress        = shopAddressInfo;
            this.menuGroupList      = menuGroupInfoList;
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
        private final String regDate;                        // 등록일
        private final String modDate;                        // 수정일

        public ShopBusinessInfo(ShopBusiness shopBusiness){
            this.shopBusinessId                 = shopBusiness.getShopBusinessId();
            this.shopBusinessTaxationType       = shopBusiness.getShopBusinessTaxationType();
            this.shopBusinessStatus             = shopBusiness.getShopBusinessStatus();
            this.shopBusinessCategory           = shopBusiness.getShopBusinessCategory();
            this.shopBusinessName               = shopBusiness.getShopBusinessName();
            this.shopBusinessRepresentativeType = shopBusiness.getShopBusinessRepresentativeType();
            this.shopBusinessRepresentativeName = shopBusiness.getShopBusinessRepresentativeName();
            this.shopBusinessLocation           = shopBusiness.getShopBusinessLocation();
            this.regDate                        = shopBusiness.getRegDate().toString();
            this.modDate                        = shopBusiness.getModDate().toString();
        }
    }

    @Getter
    @ToString
    public static class ShopMain{
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
        private final String regDate;            // 등록일
        private final String modDate;            // 수정일
        private final Shop.Status status;        // 상태

        public ShopMain(Shop shop){
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
            this.regDate            = shop.getRegDate().toString();
            this.modDate            = shop.getModDate().toString();
            this.status             = shop.getStatus();
        }
    }

    @Getter
    @ToString
    public static class ShopAddressInfo{
        private final AddressFragment addressFragment; // 가게 주소
        private final String detail;                   // 가게 상세 주소
        private final String regDate;                  // 등록일
        private final String modDate;                  // 수정일

        public ShopAddressInfo(ShopAddress shopAddress){
            this.addressFragment = shopAddress.getAddressFragment();
            this.detail          = shopAddress.getDetail();
            this.regDate         = shopAddress.getRegDate().toString();
            this.modDate         = shopAddress.getModDate().toString();
        }
    }

    @Getter
    @ToString
    public static class MenuGroupInfo{
        private final Long id;
        private final String menuGroupName;
        private final Long ordering;
        private final String regDate;
        private final String modDate;
        private final List<MenuInfo> menuList;

        public MenuGroupInfo(MenuGroup menuGroup, List<MenuInfo> menuInfoList){
            this.id            = menuGroup.getId();
            this.menuGroupName = menuGroup.getMenuGroupName();
            this.ordering      = menuGroup.getOrdering();
            this.regDate       = menuGroup.getRegDate().toString();
            this.modDate       = menuGroup.getModDate().toString();
            this.menuList      = menuInfoList;
        }
    }

    @Getter
    @ToString
    public static class MenuInfo{
        private final Long id;
        private final String menuName;
        private final String menuPriceName;
        private final Long menuPrice;
        private final String menuPhoto;
        private final String content;
        private final Long ordering;
        private final String regDate;
        private final String modDate;
        private final List<MenuOptionGroupInfo> menuOptionGroupList;

        public MenuInfo(Menu menu, List<MenuOptionGroupInfo> menuOptionGroupInfoList){
            this.id            = menu.getId();
            this.menuName      = menu.getMenuName();
            this.menuPriceName = menu.getMenuPriceName();
            this.menuPrice     = menu.getMenuPrice();
            this.menuPhoto     = menu.getMenuPhoto();
            this.content       = menu.getContent();
            this.ordering      = menu.getOrdering();
            this.regDate       = menu.getRegDate().toString();
            this.modDate       = menu.getModDate().toString();
            this.menuOptionGroupList = menuOptionGroupInfoList;
        }
    }

    @Getter
    @ToString
    public static class MenuOptionGroupInfo{
        private final Long id;
        private final String menuOptionGroupName;
        private final Long ordering;
        private final String regDate;
        private final String modDate;
        private final List<MenuOptionInfo> menuOptionList;

        public MenuOptionGroupInfo(MenuOptionGroup menuOptionGroup, List<MenuOptionInfo> menuOptionList){
            this.id                  = menuOptionGroup.getId();
            this.menuOptionGroupName = menuOptionGroup.getMenuOptionGroupName();
            this.ordering            = menuOptionGroup.getOrdering();
            this.regDate             = menuOptionGroup.getRegDate().toString();
            this.modDate             = menuOptionGroup.getModDate().toString();
            this.menuOptionList      = menuOptionList;
        }
    }

    @Getter
    @ToString
    public static class MenuOptionInfo{
        private final Long id;
        private final String menuOptionName;
        private final Long menuOptionPrice;
        private final Long ordering;
        private final String regDate;
        private final String modDate;

        public MenuOptionInfo(MenuOption menuOption){
            this.id              = menuOption.getId();
            this.menuOptionName  = menuOption.getMenuOptionName();
            this.menuOptionPrice = menuOption.getMenuOptionPrice();
            this.ordering        = menuOption.getOrdering();
            this.regDate         = menuOption.getRegDate().toString();
            this.modDate         = menuOption.getModDate().toString();
        }
    }

    @Getter
    @ToString
    public static class ShopOrderList{
        private String orderToken;
        private String memberToken;
        private String shopToken;
        private Long totalAmount;
        private String region1DepthName;
        private String region2DepthName;
        private String region3DepthName;
        private ZonedDateTime orderDate;
        private String status;
        private List<ShopOrderMenu> shopOrderMenuList;

        public ShopOrderList(Order order, List<ShopOrderMenu> shopOrderMenuList){
            this.orderToken        = order.getOrderToken();
            this.memberToken       = order.getMemberToken();
            this.shopToken         = order.getShopToken();
            this.totalAmount       = order.getTotalAmount();
            this.region1DepthName  = order.getRegion1DepthName();
            this.region2DepthName  = order.getRegion2DepthName();
            this.region3DepthName  = order.getRegion3DepthName();
            this.orderDate         = order.getOrderDate();
            this.status            = order.getStatus().toString();
            this.shopOrderMenuList = shopOrderMenuList;
        }
    }

    @Getter
    @ToString
    public static class ShopOrderMenu{
        private String orderMenuName;
        private Long orderMenuCount;
        private Long orderMenuPrice;
        private Integer ordering;
        private List<ShopOrderMenuOptionGroup> shopOrderMenuOptionGroupList;

        public ShopOrderMenu(OrderMenu orderMenu, List<ShopOrderMenuOptionGroup> shopOrderMenuOptionGroupList){
            this.orderMenuName                = orderMenu.getOrderMenuName();
            this.orderMenuCount               = orderMenu.getOrderMenuCount();
            this.orderMenuPrice               = orderMenu.getOrderMenuPrice();
            this.ordering                     = orderMenu.getOrdering();
            this.shopOrderMenuOptionGroupList = shopOrderMenuOptionGroupList;
        }
    }

    @Getter
    @ToString
    public static class ShopOrderMenuOptionGroup{
        private String orderMenuOptionGroupName;
        private Integer ordering;
        private List<ShopOrderMenuOption> shopOrderMenuOptionList;

        public ShopOrderMenuOptionGroup(OrderMenuOptionGroup orderMenuOptionGroup, List<ShopOrderMenuOption> shopOrderMenuOptionList){
            this.orderMenuOptionGroupName = orderMenuOptionGroup.getOrderMenuOptionGroupName();
            this.ordering                 = orderMenuOptionGroup.getOrdering();
            this.shopOrderMenuOptionList  = shopOrderMenuOptionList;
        }
    }

    @Getter
    @ToString
    public static class ShopOrderMenuOption{
        private String orderMenuOptionName;
        private Long orderMenuOptionPrice;
        private Integer ordering;

        public ShopOrderMenuOption(OrderMenuOption orderMenuOption){
            this.orderMenuOptionName  = orderMenuOption.getOrderMenuOptionName();
            this.orderMenuOptionPrice = orderMenuOption.getOrderMenuOptionPrice();
            this.ordering             = orderMenuOption.getOrdering();
        }
    }
}
