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
        return CommonResponse.success(orderMenuBasketList);
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
        var response = orderFacade.updateMenuBasketAmount(command);
        return CommonResponse.success(response);
    }
}
