package dev.toyproject.foodDelivery.coupon.domain;

import java.util.List;

public interface CouponRead {
    Coupon getCoupon(String couponToken);

    List<Coupon> getCouponList(String shopToken);
}
