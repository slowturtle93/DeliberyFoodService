package dev.toyproject.foodDelivery.coupon.infrastructure.issue;

import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponIssueStoreImpl implements CouponIssueStore {

    private final CouponIssueRepository couponIssueRepository;

    /**
     * 발행 쿠폰 정보 등록
     *
     * @param initCouponIssue
     * @return
     */
    @Override
    public CouponIssue save(CouponIssue initCouponIssue) {
        return couponIssueRepository.save(initCouponIssue);
    }
}
