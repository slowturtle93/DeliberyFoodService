package dev.toyproject.foodDelivery.coupon.application;

import dev.toyproject.foodDelivery.coupon.domain.CouponCommand;
import dev.toyproject.foodDelivery.coupon.domain.CouponInfo;
import dev.toyproject.foodDelivery.coupon.domain.CouponService;
import dev.toyproject.foodDelivery.coupon.domain.CouponServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponFacade {

    private final CouponService couponService;

    /**
     * 쿠폰 등록
     *
     * @param command
     * @return
     */
    public CouponInfo.Main registerCoupon(CouponCommand.Register command){
        return couponService.registerCoupon(command);
    }

    /**
     * 쿠폰 정보 수정
     *
     * @param command
     * @return
     */
    public CouponInfo.Main updateCoupon(CouponCommand.CouponUpdate command){
        return couponService.updateCoupon(command);
    }

    /**
     * 쿠폰 상태 [ENABLE] 변경
     *
     * @param couponToken
     * @return
     */
    public CouponInfo.Main enable(String couponToken){
        return couponService.enable(couponToken);
    }

}
