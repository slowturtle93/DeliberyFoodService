package dev.toyproject.foodDelivery.shop.infrastructure.menuOption;

import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuOptionStoreImpl implements MenuOptionStore {

    private final MenuOptionRepository menuOptionRepository;

    /**
     * 메뉴 옵션 등록
     *
     * @param menuOption
     * @return
     */
    @Override
    public MenuOption store(MenuOption menuOption) {
        return menuOptionRepository.save(menuOption);
    }
}
