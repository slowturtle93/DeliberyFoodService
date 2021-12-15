package dev.toyproject.foodDelivery.shop.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.shop.application.ShopFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/shop")
public class ShopController {

    private final ShopFacade shopFacade;
    private final ShopDtoMapper shopDtoMapper;

    /**
     * 사장님 가게 등록
     *
     * @param request
     * @return
     */
    @PostMapping
    public CommonResponse registerShop(@RequestBody @Valid ShopDto.ShopRequest request){
        var shopCommand = shopDtoMapper.of(request);
        var shopToken = shopFacade.registerShop(shopCommand);
        return CommonResponse.success(shopToken);
    }

    /**
     * 사장님 가게 삭제
     *
     * @param shopToken
     * @return
     */
    @PostMapping("/{shopToken}")
    public CommonResponse disableShop(@PathVariable("shopToken") String shopToken){
        shopFacade.disableShop(shopToken);
        return CommonResponse.success("OK");
    }
}
