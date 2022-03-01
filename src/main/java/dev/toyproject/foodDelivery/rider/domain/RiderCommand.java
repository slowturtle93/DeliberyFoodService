package dev.toyproject.foodDelivery.rider.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RiderCommand {

    private String riderLoginId;   // 라이더 로그인 아이디
    private String riderToken;     // 라이더 Token
    private String riderPwd;       // 라이더 비밀번호
    private String riderName;      // 라이더 이름
    private String riderTel;       // 라이더 전화번호
    private String residence;      // 라이더 거주지
    private String deliveryRegion; // 배달 지역
    private String deliveryMethod; // 배달 방법

    public Rider toEntity() {
        return Rider.builder()
                .riderLoginId(riderLoginId)
                .riderToken(riderToken)
                .riderPwd(riderPwd)
                .riderName(riderName)
                .riderTel(riderTel)
                .residence(residence)
                .deliveryRegion(deliveryRegion)
                .deliveryMethod(deliveryMethod)
                .build();
    }

    @Getter
    @Builder
    @ToString
    public static class RiderCurrentLocation{
        private String x;
        private String y;
    }

    @Getter
    @Builder
    @ToString
    public static class RiderOrderMenuCommand{
        private String orderToken;
        private String memberToken;
    }
}
