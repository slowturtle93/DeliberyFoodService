package dev.toyproject.foodDelivery.address.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class AddressFragment {

    private String addressName;      // 전체 도로명 주소
    private String region1DepthName; // 지역명1
    private String region2DepthName; // 지역명2
    private String region3DepthName; // 지역명3
    private String roadName;	     // 도로명
    private String undergroundYn;	 // 지하 여부, Y 또는 N
    private String mainBuildingNo;	 // 건물 본번
    private String subBuildingNo;	 // 건물 부번. 없을 경우 ""
    private String buildingName;	 // 건물 이름
    private String zoneNo;	         // 우편번호(5자리)
    private Double x;	             // X 좌표값, 경위도인 경우 longitude (경도)
    private Double y;	             // Y 좌표값, 경위도인 경우 latitude(위도)

    @Builder
    public AddressFragment(AddressInfo.RoadAddress roadAddress){

        this.addressName      = roadAddress.getAddressName();
        this.region1DepthName = roadAddress.getRegion1DepthName();
        this.region2DepthName = roadAddress.getRegion2DepthName();
        this.region3DepthName = roadAddress.getRegion3DepthName();
        this.roadName         = roadAddress.getRoadName();
        this.undergroundYn    = roadAddress.getUndergroundYn();
        this.mainBuildingNo   = roadAddress.getMainBuildingNo();
        this.subBuildingNo    = roadAddress.getSubBuildingNo();
        this.buildingName     = roadAddress.getBuildingName();
        this.zoneNo           = roadAddress.getZoneNo();
        this.x                = roadAddress.getX();
        this.y                = roadAddress.getY();
    }
}
