package dev.toyproject.foodDelivery.address.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class AddressInfo {

    @Getter
    @Builder
    public static class Documents {

        private final Address address;

        @SerializedName("road_address")
        private final RoadAddress roadAddress;

        @SerializedName("address_name")
        private final String addressName;	    // 전체 지번 주소 또는 전체 도로명 주소, 입력에 따라 결정됨

        @SerializedName("address_type")
        private final String addressType;	    // 	address_name의 값의 타입(Type) REGION(지명), ROAD(도로명), REGION_ADDR(지번 주소), ROAD_ADDR (도로명 주소) 중 하나

        private final String x;	                // 	X 좌표값, 경위도인 경우 longitude (경도)

        private final String y;	                // 	Y 좌표값, 경위도인 경우 latitude(위도)

    }

    @Getter
    @Builder
    public static class Meta {

        @SerializedName("total_count")
        private final Integer totalCount;

        @SerializedName("pageable_count")
        private final Integer pageableCount;

        @SerializedName("is_end")
        private final Boolean isEnd;
    }

    @Getter
    @Builder
    @ToString
    public static class RoadAddress{

        @SerializedName("address_name")
        private final String addressName;           // 전체 도로명 주소

        @SerializedName("region_1depth_name")
        private final String region1DepthName;	// 지역명1

        @SerializedName("region_2depth_name")
        private final String region2DepthName;	// 지역명2

        @SerializedName("region_3depth_name")
        private final String region3DepthName;	// 지역명3

        @SerializedName("road_name")
        private final String roadName;	            // 도로명

        @SerializedName("underground_yn")
        private final String undergroundYn;	    // 지하 여부, Y 또는 N

        @SerializedName("main_building_no")
        private final String mainBuildingNo;	    // 건물 본번

        @SerializedName("sub_building_no")
        private final String subBuildingNo;	    // 건물 부번. 없을 경우 ""

        @SerializedName("building_name")
        private final String buildingName;	        // 건물 이름

        @SerializedName("zone_no")
        private final String zoneNo;	            // 우편번호(5자리)
        private final String x;	                    // X 좌표값, 경위도인 경우 longitude (경도)
        private final String y;	                    // Y 좌표값, 경위도인 경우 latitude(위도)
    }

    @Getter
    @Builder
    public static class Address {

        @SerializedName("address_name")
        private final String addressName;       // 전체지번주소

        @SerializedName("region_1depth_name")
        private final String region1depthName;  // 지역1Depth, 시도 단위

        @SerializedName("region_2depth_name")
        private final String region2depthName;  // 지역2Depth, 구 단위

        @SerializedName("region_3depth_name")
        private final String region3depthName;  // 지역3Depth, 동 단위

        @SerializedName("region_3depth_h_name")
        private final String region3depthHName; // 지역3Depth, 행정동 명칭

        @SerializedName("h_code")
        private final String hCode;             // 행정 코드

        @SerializedName("b_code")
        private final String bCode;             // 법정코드

        @SerializedName("mountain_yn")
        private final String mountainYn;        // 산여부, Y 또는 N

        @SerializedName("main_address_no")
        private final String mainAddressNo;     // 지번 주번지

        @SerializedName("sub_address_no")
        private final String subAddressNo;      // 지번 부번지. 없을 경우 ""

        @SerializedName("zip_code")
        private final String zipCode;           // Deprecated 우편번호(6자리)
        private final String x;                 // x 좌표값, 경도위도인 경우 longitude(경도)
        private final String y;                 // Y 좌표값, 경도위도인 경우 latitude(위도)
    }
}
