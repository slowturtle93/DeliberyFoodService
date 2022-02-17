package dev.toyproject.foodDelivery.coupon.infrastructure.issue;

import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponIssueRepository extends JpaRepository<CouponIssue, Long> {
    public CouponIssue findByCouponTokenAndMemberToken(String couponToken, String memberToken);

    Optional<CouponIssue> findByCouponIssueToken(String couponIssueToken);
}
