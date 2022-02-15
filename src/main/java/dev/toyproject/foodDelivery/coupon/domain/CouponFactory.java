package dev.toyproject.foodDelivery.coupon.domain;

import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueInfo;

import java.util.List;

public interface CouponFactory {

    public List<CouponInfo.Main> convertCouponInfoMain(List<Coupon> couponList);

    public List<CouponIssueInfo.Main> convertCouponIssueInfoMain(List<CouponIssue> couponIssueList);
}
