package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopMenuFactory;
import dev.toyproject.foodDelivery.shop.domain.menu.MenuStore;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroupStore;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOptionStore;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopMenuFactoryImpl implements ShopMenuFactory {

    private final MenuGroupStore menuGroupStore;
    private final MenuStore menuStore;
    private final MenuOptionGroupStore menuOptionGroupStore;
    private final MenuOptionStore menuOptionStore;

    /**
     * 메뉴 등록
     *
     * @param shop
     * @param command
     * @return
     */
    @Override
    public List<MenuGroup> shopMenuStore(Shop shop, List<ShopCommand.MenuGroupRequest> command) {
        var menuGroupRequestList = command;

        return menuGroupRequestList.stream()
                .map(menuGroupRequest ->{
                    // menu Group store
                    var initMenuGroup = menuGroupRequest.toEntity(shop);
                    var menuGroup = menuGroupStore.store(initMenuGroup);

                    // menu store
                    menuGroupRequest.getMenuList().forEach(menuRequest ->{
                        var initMenu = menuRequest.toEntity(menuGroup);
                        var menu = menuStore.store(initMenu);

                        // menu Option Group store
                        menuRequest.getMenuOptionGroupList().forEach(menuOptionGroupRequest ->{
                            var initMenuOptionGroup = menuOptionGroupRequest.toEntity(menu);
                            var menuOptionGroup = menuOptionGroupStore.store(initMenuOptionGroup);

                            // menu Option store
                            menuOptionGroupRequest.getMenuOptionList().forEach(menuOptionRequest ->{
                                var initMenuOption = menuOptionRequest.toEntity(menuOptionGroup);
                                menuOptionStore.store(initMenuOption);
                            });
                        });
                    });

                    return menuGroup;
                }).collect(Collectors.toList());
    }
}
