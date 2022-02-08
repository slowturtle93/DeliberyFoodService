package dev.toyproject.foodDelivery.coupon.domain;

import lombok.Getter;
import lombok.ToString;

public class CouponInfo {

    @Getter
    @ToString
    public static class Main{
        private String couponToken;         // 쿠폰 토큰 정보
        private String shopToken;           // 가게 토큰 정보
        private Long discountPrice;         // 할인금액
        private String endDate;             // 행사 종료일자
        private Long expirationPeriod;   // 행사 기간
        private Coupon.Status status;       // 쿠폰 상태

        public Main(Coupon coupon){
            this.couponToken      = coupon.getCouponToken();
            this.shopToken        = coupon.getShopToken();
            this.discountPrice    = coupon.getDiscountPrice();
            this.endDate          = coupon.getEndDate();
            this.expirationPeriod = coupon.getExpirationPeriod();
            this.status           = coupon.getStatus();
        }
    }
}
