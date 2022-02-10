package dev.toyproject.foodDelivery.coupon.domain;

import dev.toyproject.foodDelivery.member.domain.Member;

public interface CouponStore {
    Coupon store(Coupon initCoupon);
}
