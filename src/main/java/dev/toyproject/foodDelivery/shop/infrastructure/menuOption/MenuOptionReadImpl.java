package dev.toyproject.foodDelivery.shop.infrastructure.menuOption;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOptionRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuOptionReadImpl implements MenuOptionRead {

    private final MenuOptionRepository menuOptionRepository;

    /**
     * 메뉴 옵션 조회
     *
     * @param id
     * @return
     */

    @Override
    public MenuOption getMenuOptionById(Long id) {
        return menuOptionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
