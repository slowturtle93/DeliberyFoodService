package dev.toyproject.foodDelivery.coupon.domain;

public interface CouponService {

    public CouponInfo.Main registerCoupon(CouponCommand.Register command);

    public CouponInfo.Main updateCoupon(CouponCommand.CouponUpdate command);

    public CouponInfo.Main enable(String couponToken);
}
