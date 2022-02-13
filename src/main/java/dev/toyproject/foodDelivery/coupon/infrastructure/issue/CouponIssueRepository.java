package dev.toyproject.foodDelivery.coupon.infrastructure.issue;

import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueRepository extends JpaRepository<CouponIssue, Long> {
    public CouponIssue findByCouponTokenAndMemberToken(String couponToken, String memberToken);
}
