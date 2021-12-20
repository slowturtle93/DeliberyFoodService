package dev.toyproject.foodDelivery.shop.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.shop.application.ShopFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * 사장님 가게 정보 수정
     *
     * @param request
     * @return
     */
    @PostMapping("/update/{shopToken}")
    public CommonResponse updateShop(@PathVariable("shopToken") String shopToken, @RequestBody @Valid ShopDto.ShopRequest request){
        var command = shopDtoMapper.of(request);
        var shopInfo = shopFacade.updateShop(shopToken, command);
        return CommonResponse.success(shopInfo);
    }

    /**
     * 메뉴 등록
     *
     * @param shopToken
     * @param request
     * @return
     */
    @PostMapping("/menu/{shopToken}")
    public CommonResponse registerMenu(@PathVariable("shopToken") String shopToken, @RequestBody @Valid List<ShopDto.MenuGroupRequest> request){
        var command = shopDtoMapper.toMenuList(request);
        var shopTokenInfo = shopFacade.registerMenu(shopToken, command);
        return CommonResponse.success(shopTokenInfo);
    }
}
