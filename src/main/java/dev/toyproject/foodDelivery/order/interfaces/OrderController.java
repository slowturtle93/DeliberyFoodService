package dev.toyproject.foodDelivery.order.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.order.application.OrderFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest paymentRequest){
        var paymentCommand = orderDtoMapper.of(paymentRequest); // Command 로 객체 변환
        orderFacade.paymentOrder(paymentCommand);                                        // 주문 결제 요청
        return CommonResponse.success("OK");
    }
}
