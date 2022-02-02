package dev.toyproject.foodDelivery.shop.interfaces;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class ShopDto {

    @Getter
    @Builder
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
        private final ShopBusinessInfoRequest shopBusiness; // 사업자 정보
        private final ShopAddressInfoRequest shopAddress;   // 가게 주소 정보
        private final List<MenuGroupRequest> menuGroupList; // 메뉴 정보
    }

    @Getter
    @Setter
    @ToString
    public static class ShopRequest{
        private String ownerToken;         // 사장님 토큰 정보
        private String shopBuildingInfoId; // 가게 건물 정보
        private String shopCategoryId;     // 가게 카테고리 정보
        private String shopNm;             // 가게명
        private String shopDeliveryRegion; // 가게 배달 지역
        private String shopTel;            // 가게 전화번호
        private String shopInfo;           // 가게 소개
        private String shopMinOrdPrice;    // 최소 주문금액
        private String shopNotice;         // 가게 공지사항
        private String shopOperatingTime;  // 가게 운영시간
        private String shopClosedDate;     // 가게 휴무일
        private String shopOrderType;      // 주문타입
        private String shopOriginInfo;     // 원산지 정보
        private ShopBusinessInfoRequest shopBusinessInfoRequest; // 사업장정보
        private ShopAddressInfoRequest shopAddressInfoRequest;   // 가게 주소 정보
    }

    @Getter
    @Setter
    @ToString
    public static class ShopBusinessInfoRequest{
        private String shopBusinessId;                 // 사업자등록번호
        private String shopBusinessTaxationType;       // 사업자과세구분
        private String shopBusinessStatus;             // 사업자 업태
        private String shopBusinessCategory;           // 사업자 종목
        private String shopBusinessName;               // 사업자 상호
        private String shopBusinessRepresentativeType; // 사업자 대표자구분
        private String shopBusinessRepresentativeName; // 사업자 대표자성명
        private String shopBusinessLocation;           // 사업장 소재지
        private String regDate;
        private String modDate;
    }

    @Getter
    @Setter
    @ToString
    public static class ShopAddressInfoRequest{
        private AddressFragment addressFragment; // 가게 주소
        private String detail;                   // 가게 상세 주소
        private String regDate;
        private String modDate;
    }

    @Getter
    @Setter
    @ToString
    public static class MenuGroupRequest{
        private Long id;
        private String menuGroupName;
        private Long ordering;
        private String regDate;
        private String modDate;
        private List<MenuRequest> menuList;
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
        private String regDate;
        private String modDate;
        private List<MenuOptionGroupRequest> menuOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class MenuOptionGroupRequest{
        private Long id;
        private String menuOptionGroupName;
        private Long ordering;
        private String regDate;
        private String modDate;
        private List<MenuOptionRequest> menuOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class MenuOptionRequest{
        private Long id;
        private String menuOptionName;
        private Long menuOptionPrice;
        private Long ordering;
        private String regDate;
        private String modDate;
    }

    @Getter
    @Setter
    @ToString
    public static class MemberLocationRequest{
        private Double x;
        private Double y;
    }

    @Getter
    @Setter
    @ToString
    public static class ShopOrderMenuRequest{
        private String ownerToken;
        private String shopToken;
    }

    /******************************** response ********************************/

    @Getter
    @Builder
    @ToString
    public static class Response{
        private final String ownerToken;         // 사장님 토큰 정보
        private final String shopToken;          // 가게 토큰 정보
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
    }
}
