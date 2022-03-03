package dev.toyproject.foodDelivery.coupon.infrastructure;

import dev.toyproject.foodDelivery.coupon.domain.Coupon;
import dev.toyproject.foodDelivery.coupon.domain.CouponStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponStoreImpl implements CouponStore {

    private final CouponRepository couponRepository;

    /**
     * 쿠폰 저장
     *
     * @param initCoupon
     * @return
     */
    @Override
    public Coupon store(Coupon initCoupon) {
        return couponRepository.save(initCoupon);
    }
}
