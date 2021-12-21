package dev.toyproject.foodDelivery.shop.infrastructure.menuGroup;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroupRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuGroupReadImpl implements MenuGroupRead {

    private final MenuGroupRepository menuGroupRepository;

    /**
     * 메뉴 그룹 조회
     *
     * @param id
     * @return
     */
    @Override
    public MenuGroup getMenuGroupById(Long id) {
        return menuGroupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
