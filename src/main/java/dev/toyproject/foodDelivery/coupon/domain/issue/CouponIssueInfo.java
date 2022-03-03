package dev.toyproject.foodDelivery.coupon.domain.issue;

import lombok.Getter;
import lombok.ToString;

public class CouponIssueInfo {

    @Getter
    @ToString
    public static class Main{
        private String couponIssueToken;
        private String couponToken;
        private String memberToken;
        private CouponIssue.Status status;

        public Main(CouponIssue couponIssue){
            this.couponIssueToken = couponIssue.getCouponIssueToken();
            this.couponToken      = couponIssue.getCouponToken();
            this.memberToken      = couponIssue.getMemberToken();
            this.status           = couponIssue.getStatus();
        }
    }
}
