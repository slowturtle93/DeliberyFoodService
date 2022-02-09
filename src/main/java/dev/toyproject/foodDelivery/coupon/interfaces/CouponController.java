package dev.toyproject.foodDelivery.coupon.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.coupon.application.CouponFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/coupon")
public class CouponController {

    private final CouponFacade couponFacade;
    private final CouponDtoMapper couponDtoMapper;

    /**
     * 쿠폰 등록
     *
     * @param request
     * @return
     */
    @PostMapping("/init")
    public CommonResponse registerCoupon(@RequestBody @Valid CouponDto.Register request){
        var command = couponDtoMapper.of(request);
        var response = couponFacade.registerCoupon(command);
        return CommonResponse.success(response);
    }

    /**
     *  쿠폰 정보 수정
     *
     * @param request
     * @return
     */
    @PatchMapping("/update")
    public CommonResponse updateCoupon(@RequestBody @Valid CouponDto.CouponUpdate request){
        var command = couponDtoMapper.of(request);
        var response = couponFacade.updateCoupon(command);
        return CommonResponse.success(response);
    }

    /**
     * 쿠폰 상태 [ENABLE] 변경
     *
     * @param couponToken
     * @return
     */
    @PatchMapping("/enable/{couponToken}")
    public CommonResponse enable(@PathVariable("couponToken") String couponToken){
        var response = couponFacade.enable(couponToken);
        return CommonResponse.success(response);
    }
}
