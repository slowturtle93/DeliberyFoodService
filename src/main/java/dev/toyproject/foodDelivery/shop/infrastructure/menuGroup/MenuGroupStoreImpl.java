package dev.toyproject.foodDelivery.shop.infrastructure.menuGroup;

import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuGroupStoreImpl implements MenuGroupStore {

    private final MenuGroupRepository menuGroupRepository;

    /**
     * 메뉴 그룹 등록
     *
     * @param menuGroup
     * @return
     */
    @Override
    public MenuGroup store(MenuGroup menuGroup) {
        return menuGroupRepository.save(menuGroup);
    }
}
