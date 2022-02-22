package dev.toyproject.foodDelivery.rider.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RiderInfo {

    private String riderLoginId;   // 라이더 로그인 아이디
    private String riderToken;     // 라이더 Token
    private String riderPwd;       // 라이더 비밀번호
    private String riderName;      // 라이더 이름
    private String riderTel;       // 라이더 전화번호
    private String residence;      // 라이더 거주지
    private String deliveryRegion; // 배달 지역
    private String deliveryMethod; // 배달 방법
    private Rider.Status status;   // 라이더 상태

    @Builder
    public RiderInfo(Rider rider){
        this.riderLoginId   = rider.getRiderLoginId();
        this.riderToken     = rider.getRiderToken();
        this.riderPwd       = rider.getRiderTel();
        this.riderName      = rider.getRiderName();
        this.riderTel       = rider.getRiderTel();
        this.residence      = rider.getResidence();
        this.deliveryRegion = rider.getDeliveryRegion();
        this.deliveryMethod = rider.getDeliveryMethod();
        this.status         = rider.getStatus();
    }

    @Getter
    @Builder
    @ToString
    public static class AvailableOrders{
        private String orderToken;
        private String toRegion1DepthName;
        private String toRegion2DepthName;
        private String toRegion3DepthName;
        private String toAddressDetail;
        private String shopName;
        private String fromRegion1DepthName;
        private String fromRegion2DepthName;
        private String fromRegion3DepthName;
        private String fromAddressDetail;
        private String distance;
    }
}
