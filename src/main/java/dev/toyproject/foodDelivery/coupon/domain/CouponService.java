package dev.toyproject.foodDelivery.coupon.domain;

public interface CouponService {

    public CouponInfo.Main registerCoupon(CouponCommand.Register command);
}
