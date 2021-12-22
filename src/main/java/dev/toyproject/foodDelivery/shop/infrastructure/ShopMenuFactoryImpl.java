package dev.toyproject.foodDelivery.shop.infrastructure;

import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.ShopMenuFactory;
import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import dev.toyproject.foodDelivery.shop.domain.menu.MenuRead;
import dev.toyproject.foodDelivery.shop.domain.menu.MenuStore;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroupRead;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroupStore;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOptionRead;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOptionStore;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroupRead;
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

    private final MenuGroupRead menuGroupRead;
    private final MenuRead menuRead;
    private final MenuOptionRead menuOptionRead;
    private final MenuOptionGroupRead menuOptionGroupRead;

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

    /**
     * 메뉴 수정
     *
     * @param command
     * @return
     */
    @Override
    public void updateMenu(List<ShopCommand.MenuGroupRequest> command) {
        var menuGroupList = command;

        // Menu Group
        menuGroupList.forEach(menuGroupInfo ->{
            var menuList = menuGroupInfo.getMenuList();

            // Menu
            menuList.forEach(menuInfo -> {
                var menuOptionGroupList = menuInfo.getMenuOptionGroupList();
                // Menu Option Group
                menuOptionGroupList.forEach(menuOptionGroupInfo ->{
                    var menuOptionList = menuOptionGroupInfo.getMenuOptionList();
                    // Menu Option
                    menuOptionList.forEach(menuOptionInfo -> {
                        MenuOption menuOption = menuOptionRead.getMenuOptionById(menuOptionInfo.getId());
                        menuOption.update(menuOptionInfo);
                    });
                    MenuOptionGroup menuOptionGroup = menuOptionGroupRead.getMenuOptionGroupById(menuOptionGroupInfo.getId());
                    menuOptionGroup.update(menuOptionGroupInfo);
                });
                Menu menu = menuRead.getMenuById(menuInfo.getId());
                menu.update(menuInfo);
            });
            MenuGroup menuGroup = menuGroupRead.getMenuGroupById(menuGroupInfo.getId());
            menuGroup.update(menuGroupInfo);
        });
    }

    /**
     * 메뉴 그룹 삭제
     *
     * @param command
     */
    @Override
    public void deleteMenuGroup(ShopCommand.MenuGroupRequest command) {
        MenuGroup menuGroup = menuGroupRead.getMenuGroupById(command.getId());

        var menuInfo = menuGroup.getMenuList();

        // menu delete
        menuInfo.forEach(menu -> {

            // menu Option Group delete
            var menuOptionGroupInfo = menu.getMenuOptionGroupList();
            menuOptionGroupInfo.forEach(menuOptionGroup -> {

                // menu Option delete
                var menuOptionInfo = menuOptionGroup.getMenuOptionList();
                menuOptionInfo.forEach(menuOption -> {
                    menuOptionStore.delete(menuOption);
                });
                menuOptionGroupStore.delete(menuOptionGroup);
            });
            menuStore.delete(menu);
        });
        menuGroupStore.delete(menuGroup);
    }

}
