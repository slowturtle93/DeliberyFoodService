package dev.toyproject.foodDelivery.coupon.infrastructure;

import dev.toyproject.foodDelivery.coupon.domain.Coupon;
import dev.toyproject.foodDelivery.coupon.domain.CouponFactory;
import dev.toyproject.foodDelivery.coupon.domain.CouponInfo;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponFactoryImpl implements CouponFactory {

    /**
     * CouponInfo Response Convert
     *
     * @param couponList
     * @return
     */
    @Override
    public List<CouponInfo.Main> convertCouponInfoMain(List<Coupon> couponList) {
        return couponList.stream()
                .map(CouponInfo.Main::new)
                .collect(Collectors.toList());
    }

    /**
     * CouponIssueInfo Response Convert
     *
     * @param couponIssueList
     * @return
     */
    @Override
    public List<CouponIssueInfo.Main> convertCouponIssueInfoMain(List<CouponIssue> couponIssueList) {
        return couponIssueList.stream()
                .map(CouponIssueInfo.Main::new)
                .collect(Collectors.toList());
    }
}
