package dev.toyproject.foodDelivery.coupon.infrastructure.issue;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueCommand;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponIssueReadImpl implements CouponIssueRead {

    private final CouponIssueRepository couponIssueRepository;

    /**
     * 쿠폰 발행 중복 체크
     *
     * @param command
     * @return
     */
    @Override
    public boolean duplicationCouponIssue(CouponIssueCommand.Main command) {
        var couponIssue = couponIssueRepository.findByCouponTokenAndMemberToken(command.getCouponToken(), command.getMemberToken());

        if (couponIssue == null) return false;
        else return true;
    }

    /**
     * 발행된 쿠폰 조회
     *
     * @param couponIssueToken
     * @return
     */
    @Override
    public CouponIssue getCouponIssue(String couponIssueToken) {
        return couponIssueRepository.findByCouponIssueToken(couponIssueToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
