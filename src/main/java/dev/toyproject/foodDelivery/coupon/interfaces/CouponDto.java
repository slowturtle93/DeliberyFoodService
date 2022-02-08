package dev.toyproject.foodDelivery.coupon.interfaces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

public class CouponDto {

    @Getter
    @Setter
    @ToString
    public static class Register{
        private String  shopToken;
        private Long discountPrice;
        private String endDate;
        private Long expirationPeriod;
    }
}
