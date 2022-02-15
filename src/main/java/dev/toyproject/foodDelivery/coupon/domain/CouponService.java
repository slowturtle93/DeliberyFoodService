package dev.toyproject.foodDelivery.coupon.domain;

import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueCommand;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueInfo;

import java.util.List;

public interface CouponService {

    public CouponInfo.Main registerCoupon(CouponCommand.Register command);

    public CouponInfo.Main updateCoupon(CouponCommand.CouponUpdate command);

    public CouponInfo.Main enable(String couponToken);

    public List<CouponInfo.Main> retrieveCouponList(String shopToken);

    public List<CouponInfo.Main> retrieveCouponEnable(String shopToken);

    public CouponIssueInfo.Main registerCouponIssue(CouponIssueCommand.Main command);

    public List<CouponIssueInfo.Main> retrieveCouponIssue(CouponIssueCommand.RetrieveCouponIssue command);
}
