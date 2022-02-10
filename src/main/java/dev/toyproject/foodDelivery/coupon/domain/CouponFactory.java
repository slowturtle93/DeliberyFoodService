package dev.toyproject.foodDelivery.coupon.domain;

import java.util.List;

public interface CouponFactory {

    public List<CouponInfo.Main> convertCouponInfoMain(List<Coupon> couponList);
}
