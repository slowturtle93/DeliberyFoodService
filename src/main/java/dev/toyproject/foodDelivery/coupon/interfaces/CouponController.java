package dev.toyproject.foodDelivery.coupon.interfaces;

import dev.toyproject.foodDelivery.common.aop.LoginCheck;
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
    @LoginCheck(type = LoginCheck.UserType.OWNER)
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
    @LoginCheck(type = LoginCheck.UserType.OWNER)
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
    @LoginCheck(type = LoginCheck.UserType.OWNER)
    public CommonResponse enable(@PathVariable("couponToken") String couponToken){
        var response = couponFacade.enable(couponToken);
        return CommonResponse.success(response);
    }

    /**
     * 등록 된 쿠폰 list 조회
     *
     * @return
     */
    @GetMapping("/retrieve/{shopToken}")
    @LoginCheck(type = LoginCheck.UserType.OWNER)
    public CommonResponse retrieveCouponList(@PathVariable("shopToken") String shopToken){
        var response = couponFacade.retrieveCouponList(shopToken);
        return CommonResponse.success(response);
    }

    /**
     * 발행 가능한 쿠폰 조회
     *
     * @param shopToken
     * @return
     */
    @GetMapping("/issue/retrieve/{shopToken}")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse retrieveCouponEnable(@PathVariable("shopToken") String shopToken){
        var response = couponFacade.retrieveCouponEnable(shopToken);
        return CommonResponse.success(response);
    }

    /**
     * 사용자 쿠폰 발행
     *
     * @return
     */
    @PostMapping("/issue")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse registerCouponIssue(@RequestBody @Valid CouponDto.RegisterCouponIssue request){
        var command = couponDtoMapper.of(request);
        var response = couponFacade.registerCouponIssue(command);
        return CommonResponse.success(response);
    }
}
