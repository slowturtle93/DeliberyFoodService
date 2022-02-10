package dev.toyproject.foodDelivery.coupon.infrastructure;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.coupon.domain.Coupon;
import dev.toyproject.foodDelivery.coupon.domain.CouponRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponReadImpl implements CouponRead {

    private final CouponRepository couponRepository;

    /**
     * 쿠폰 정보 조회
     *
     * @param couponToken
     * @return
     */
    @Override
    public Coupon getCoupon(String couponToken) {
        return couponRepository.findByCouponToken(couponToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Coupon> getCouponList(String shopToken) {
        return couponRepository.findByShopToken(shopToken);
    }
}
