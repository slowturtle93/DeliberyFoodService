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

}
