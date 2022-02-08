package dev.toyproject.foodDelivery.coupon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CouponCommand {

    @Getter
    @Builder
    @ToString
    public static class Register{
        private String  shopToken;
        private Long discountPrice;
        private String endDate;
        private Long expirationPeriod;

        public Coupon toEntity(){
            return Coupon.builder()
                    .shopToken(shopToken)
                    .discountPrice(discountPrice)
                    .endDate(endDate)
                    .expirationPeriod(expirationPeriod)
                    .build();
        }
    }
}
