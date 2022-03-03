package dev.toyproject.foodDelivery.order.interfaces;

import dev.toyproject.foodDelivery.common.aop.LoginCheck;
import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.common.util.redis.SessionUtil;
import dev.toyproject.foodDelivery.order.application.OrderFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderFacade orderFacade;
    private final OrderDtoMapper orderDtoMapper;

    /**
     * 장바구니 메뉴 등록
     *
     * @param request
     * @return
     */
    @PostMapping("/basket")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse registerMenuBasket(@RequestBody @Valid OrderDto.OrderBasketRequest request){
        var command = orderDtoMapper.of(request);
        var orderMenuBasketList = orderFacade.registerMenuBasket(command);
        var response = orderDtoMapper.orderMenuListResponse(orderMenuBasketList);
        return CommonResponse.success(response);
    }

    /**
     * 장바구니 특정 메뉴 삭제
     *
     * @param request
     * @return
     */
    @DeleteMapping("/basket")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse deleteMenuBasket(@RequestBody @Valid OrderDto.OrderBasketRequest request){
        var command = orderDtoMapper.of(request);
        var menuBasketInfoList = orderFacade.deleteMenuBasket(command);
        var response = orderDtoMapper.orderMenuListResponse(menuBasketInfoList);
        return CommonResponse.success(menuBasketInfoList);
    }

    /**
     * 장바구니 메뉴 전체 삭제
     *
     * @param memberToken
     * @return
     */
    @DeleteMapping("/basket/all/{memberToken}")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse deleteMenuBasketAll(@PathVariable("memberToken") String memberToken){
        orderFacade.deleteMenuBasketAll(memberToken);
        return CommonResponse.success("OK");
    }

    /**
     * 장바구니 메뉴 전체 조회
     *
     * @param memberToken
     * @return
     */
    @GetMapping("/basket/{memberToken}")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse retrieveMenuBasket(@PathVariable("memberToken") String memberToken){
        var menuBasketInfoList = orderFacade.retrieveMenuBasket(memberToken);
        var response = orderDtoMapper.orderMenuListResponse(menuBasketInfoList);
        return CommonResponse.success(menuBasketInfoList);
    }

    /**
     * Redis 장바구니 메뉴 수량 변경
     *
     * @param request
     * @return
     */
    @PatchMapping("basket/amount_update")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse updateMenuBasketAmount(@RequestBody @Valid OrderDto.OrderBasketRequest request){
        var command = orderDtoMapper.of(request);
        var menuBasketInfoList = orderFacade.updateMenuBasketAmount(command);
        var response = orderDtoMapper.orderMenuListResponse(menuBasketInfoList);
        return CommonResponse.success(response);
    }

    /**
     * 주문 정보 저장
     *
     * @param request
     * @return
     */
    @PostMapping("/init")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request){
        var orderCommand = orderDtoMapper.of(request);
        var orderToken = orderFacade.registerOrder(orderCommand);
        var response = orderDtoMapper.of(orderToken);
        return CommonResponse.success(response);
    }

    /**
     * 주문 정보 조회
     *
     * @param orderToken
     * @return
     */
    @GetMapping("/{orderToken}")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse retrieveOrder(@PathVariable String orderToken){
        var orderInfo = orderFacade.retrieveOrder(orderToken); // 주문 정보 조회
        var response = orderDtoMapper.of(orderInfo);           // 주문 정보 객체 변환
        return CommonResponse.success(response);
    }

    /**
     * 사용자의 주문 이력 list 조회
     *
     * @param memberToken
     * @return
     */
    @GetMapping("list/{memberToken}")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse retrieveOrderList(@PathVariable("memberToken") String memberToken){
        var orderInfoList = orderFacade.retrieveOrderList(memberToken);
        var response = orderDtoMapper.orderInfoList(orderInfoList);
        return CommonResponse.success(response);
    }

    /**
     * 주문 결제 요청
     *
     * @param paymentRequest
     * @return
     */
    @PostMapping("/payment")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest paymentRequest){
        var paymentCommand = orderDtoMapper.of(paymentRequest);   // Command 로 객체 변환
        var response = orderFacade.paymentOrder(paymentCommand); // 주문 결제 요청
        return CommonResponse.success(response);
    }

    /**
     * 카카오페이 주문 결제 성공 API 요청
     *
     * @param pg_token
     * @return
     */
    @GetMapping("/kakao/success")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse orderPaymentKakaoSuccess(@RequestParam("pg_token") String pg_token, HttpSession session){
        String pgToken = pg_token;
        String memberToken = SessionUtil.getLoginMemberToken(session);
        orderFacade.orderPaymentKakaoSuccess(pgToken, memberToken);
        return CommonResponse.success("OK");
    }

    /**
     * 토스페이 결제 성공 callback 결과
     *
     * @param callbackRequest
     * @return
     */
    @PostMapping("/toss/callback")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse orderPaymentTossCallback(
            @RequestBody @Valid OrderDto.orderPaymentTossCallbackRequest callbackRequest,
            HttpSession session){
        var paymentToken = callbackRequest.getPayToken();
        var memberToken = SessionUtil.getLoginMemberToken(session);
        orderFacade.orderPaymentTossSuccess(paymentToken, memberToken);
        return CommonResponse.success("OK");
    }

    /**
     * 주문 정보 [주문 승인] 상태 변경
     *
     * @param request
     * @return
     */
    @PostMapping("/approval")
    public CommonResponse OrderApproval(@RequestBody @Valid OrderDto.OrderPaymentStatusRequest request){
        var command = orderDtoMapper.of(request);
        orderFacade.orderApproval(command);
        return CommonResponse.success("OK");
    }

    /**
     * 주문 정보 [주문 취소] 상태 변경
     *
     * @param request
     * @return
     */
    @PostMapping("/cancel")
    public CommonResponse OrderCancel(@RequestBody @Valid OrderDto.OrderPaymentStatusRequest request){
        var command = orderDtoMapper.of(request);
        orderFacade.orderCancel(command);
        return CommonResponse.success("OK");
    }

    /**
     * 주문 정보 [배송 준비] 상태 변경
     *
     * @param request
     * @return
     */
    @PostMapping("/delivery/prepare")
    public CommonResponse OrderDeliveryPrepare(@RequestBody @Valid OrderDto.OrderPaymentStatusRequest request){
        var command = orderDtoMapper.of(request);
        orderFacade.OrderDeliveryPrepare(command);
        return CommonResponse.success("OK");
    }

    /**
     * 주문 정보 [배송 준비] 상태 변경
     *
     * @param request
     * @return
     */
    @PostMapping("/delivery/in")
    public CommonResponse OrderInDelivery(@RequestBody @Valid OrderDto.OrderPaymentStatusRequest request){
        var command = orderDtoMapper.of(request);
        orderFacade.OrderInDelivery(command);
        return CommonResponse.success("OK");
    }

    /**
     * 주문 정보 [배달완료] 상태 변경
     *
     * @param request
     * @return
     */
    @PostMapping("/delivery/complete")
    public CommonResponse OrderComplete(@RequestBody @Valid OrderDto.OrderPaymentStatusRequest request){
        var command = orderDtoMapper.of(request);
        orderFacade.OrderComplete(command);
        return CommonResponse.success("OK");
    }
}
