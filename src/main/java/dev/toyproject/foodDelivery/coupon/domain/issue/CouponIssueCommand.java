package dev.toyproject.foodDelivery.coupon.domain.issue;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CouponIssueCommand {

    @Getter
    @Builder
    @ToString
    public static class Main{
        private String couponToken;
        private String memberToken;

        public CouponIssue toEntity(){
            return CouponIssue.builder()
                    .couponToken(couponToken)
                    .memberToken(memberToken)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RetrieveCouponIssue{
        private String shopToken;
        private String memberToken;
    }
}
