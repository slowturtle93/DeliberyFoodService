package dev.toyproject.foodDelivery.shop.interfaces;

import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel       = "spring",
        injectionStrategy    = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ShopDtoMapper {

    /******************* request  *********************/

    ShopCommand.ShopRequest of(ShopDto.ShopRequest request);

    List<ShopCommand.MenuGroupRequest> toMenuList(List<ShopDto.MenuGroupRequest> menuGroupRequestList);

    ShopCommand.MenuGroupRequest of(ShopDto.MenuGroupRequest request);

    ShopCommand.MenuRequest of(ShopDto.MenuRequest request);

    ShopCommand.MenuOptionGroupRequest of(ShopDto.MenuOptionGroupRequest request);

    ShopCommand.MenuOptionRequest of(ShopDto.MenuOptionRequest request);

    ShopCommand.MemberLocationRequest of(ShopDto.MemberLocationRequest request);

    ShopCommand.ShopOrderMenuRequest of(ShopDto.ShopOrderMenuRequest request);

    ShopCommand.ShopOrderConfirmRequest of(ShopDto.ShopOrderConfirmRequest request);

    /******************* response  *********************/

    ShopDto.Main of(ShopInfo.Main main);

    List<ShopDto.Response> of(List<ShopInfo.ShopMain> shopMain);
}
