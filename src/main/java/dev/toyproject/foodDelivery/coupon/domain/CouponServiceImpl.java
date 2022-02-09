package dev.toyproject.foodDelivery.coupon.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponStore couponStore;
    private final CouponRead couponRead;

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

    /**
     * 쿠폰 정보 수정
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public CouponInfo.Main updateCoupon(CouponCommand.CouponUpdate command) {
        var coupon = couponRead.getCoupon(command.getCouponToken());
        coupon.update(command);
        return new CouponInfo.Main(coupon);
    }

    /**
     * 쿠폰 상태 [ENABLE] 변경
     *
     * @param couponToken
     * @return
     */
    @Override
    @Transactional
    public CouponInfo.Main enable(String couponToken) {
        var coupon = couponRead.getCoupon(couponToken);
        coupon.enable();
        return new CouponInfo.Main(coupon);
    }
}
