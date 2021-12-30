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

    /**
     * 메뉴 수정
     *
     * @param request
     * @return
     */
    @PatchMapping("/menu/update")
    public CommonResponse updateMenu(@RequestBody @Valid List<ShopDto.MenuGroupRequest> request){
        var command = shopDtoMapper.toMenuList(request);
        shopFacade.updateMenu(command);
        return CommonResponse.success("OK");
    }

    /**
     * 메뉴 그룹 삭제
     *
     * @param request
     * @return
     */
    @DeleteMapping("/delete/menuGroup")
    public CommonResponse deleteMenuGroup(@RequestBody @Valid ShopDto.MenuGroupRequest request){
        var command = shopDtoMapper.of(request);
        shopFacade.deleteMenuGroup(command);
        return CommonResponse.success("OK");
    }

    /**
     * 메뉴 삭제
     *
     * @param request
     * @return
     */
    @DeleteMapping("/delete/menu")
    public CommonResponse deleteMenu(@RequestBody @Valid ShopDto.MenuRequest request){
        var command = shopDtoMapper.of(request);
        shopFacade.deleteMenu(command);
        return CommonResponse.success("OK");
    }

    /**
     * 메뉴 옵션 그룹 삭제
     *
     * @param request
     * @return
     */
    @DeleteMapping("/delete/menuOptionGroup")
    public CommonResponse deleteMenuOptionGroup(@RequestBody @Valid ShopDto.MenuOptionGroupRequest request){
        var command = shopDtoMapper.of(request);
        shopFacade.deleteMenuOptionGroup(command);
        return CommonResponse.success("OK");
    }

    /**
     * 메뉴 옵션 삭제
     *
     * @param request
     * @return
     */
    @DeleteMapping("/delete/menuOption")
    public CommonResponse deleteMenuOption(@RequestBody @Valid ShopDto.MenuOptionRequest request){
        var command = shopDtoMapper.of(request);
        shopFacade.deleteMenuOption(command);
        return CommonResponse.success("OK");
    }

    /**
     * 사장님 특정 가게 조회
     *
     * @param shopToken
     * @return
     */
    @GetMapping("/{shopToken}")
    public CommonResponse retrieveShop(@PathVariable("shopToken") String shopToken){
        var shopInfo = shopFacade.retrieveShopInfo(shopToken);
        var response = shopDtoMapper.of(shopInfo);
        return CommonResponse.success(response);
    }

    /**
     * 사용자의 위치 기반으로 반경 2km 이내의 가게 조회
     *
     * @param request
     * @return
     */
    @PostMapping("/search")
    public CommonResponse searchShop(@RequestBody @Valid ShopDto.MemberLocationRequest request){
        var memberLocationInfo = shopDtoMapper.of(request);
        var shopInfo = shopFacade.searchShop(memberLocationInfo);
        var response = shopDtoMapper.of(shopInfo);
        return CommonResponse.success(response);
    }
}
