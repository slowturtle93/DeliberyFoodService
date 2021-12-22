package dev.toyproject.foodDelivery.shop.infrastructure.menuOptionGroup;

import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuOptionGroupStoreImpl implements MenuOptionGroupStore {

    private final MenuOptionGroupRepository menuOptionGroupRepository;

    /**
     * 메뉴 옵션 그룹 등록
     *
     * @param menuOptionGroup
     * @return
     */
    @Override
    public MenuOptionGroup store(MenuOptionGroup menuOptionGroup) {
        return menuOptionGroupRepository.save(menuOptionGroup);
    }

    /**
     * 메뉴 옵션 그룹 삭제
     *
     * @param menuOptionGroup
     */
    @Override
    public void delete(MenuOptionGroup menuOptionGroup) {
        menuOptionGroupRepository.delete(menuOptionGroup);
    }
}
