package dev.toyproject.foodDelivery.shop.infrastructure.menu;

import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import dev.toyproject.foodDelivery.shop.domain.menu.MenuStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuStoreImpl implements MenuStore {

    private final MenuRepository menuRepository;

    /**
     * 메뉴 등록
     *
     * @param menu
     * @return
     */
    @Override
    public Menu store(Menu menu) {
        return menuRepository.save(menu);
    }

    /**
     * 메뉴 삭제
     *
     * @param menu
     */
    @Override
    public void delete(Menu menu) {
        menuRepository.delete(menu);
    }
}
