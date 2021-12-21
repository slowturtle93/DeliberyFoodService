package dev.toyproject.foodDelivery.shop.infrastructure.menu;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import dev.toyproject.foodDelivery.shop.domain.menu.MenuRead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuReadImpl implements MenuRead {

    private final MenuRepository menuRepository;

    /**
     * 메뉴 조회
     *
     * @param id
     * @return
     */
    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
