package dev.toyproject.foodDelivery.coupon.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponStore couponStore;

    /**
     * 쿠폰 등록
     *
     * @param command
     * @return
     */
    @Override
    public CouponInfo.Main registerCoupon(CouponCommand.Register command) {
        var coupon = couponStore.store(command.toEntity());
        return new CouponInfo.Main(coupon);
    }
}
