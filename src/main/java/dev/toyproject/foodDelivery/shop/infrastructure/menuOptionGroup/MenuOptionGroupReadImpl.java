package dev.toyproject.foodDelivery.shop.infrastructure.menuOptionGroup;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroupRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuOptionGroupReadImpl implements MenuOptionGroupRead {

    private final MenuOptionGroupRepository menuOptionGroupRepository;

    /**
     * 메뉴 옵션 그룹 조회
     *
     * @param id
     * @return
     */
    @Override
    public MenuOptionGroup getMenuOptionGroupById(Long id) {
        return menuOptionGroupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
