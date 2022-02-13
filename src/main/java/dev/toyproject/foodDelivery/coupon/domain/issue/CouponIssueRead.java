package dev.toyproject.foodDelivery.coupon.domain.issue;

public interface CouponIssueRead {
    public boolean duplicationCouponIssue(CouponIssueCommand.Main command);
}
