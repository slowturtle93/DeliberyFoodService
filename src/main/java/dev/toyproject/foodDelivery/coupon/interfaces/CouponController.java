package dev.toyproject.foodDelivery.coupon.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.coupon.application.CouponFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
