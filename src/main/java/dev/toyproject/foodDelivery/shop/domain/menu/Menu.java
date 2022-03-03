package dev.toyproject.foodDelivery.shop.domain.menu;

import com.google.common.collect.Lists;
import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.menuGroup.MenuGroup;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "menu")
public class Menu extends AbstracEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName;
    private String menuPriceName;
    private Long menuPrice;
    private String menuPhoto;
    private String content;
    private Long ordering;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = CascadeType.PERSIST)
    private List<MenuOptionGroup> menuOptionGroupList = Lists.newArrayList();


    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Menu(
            MenuGroup menuGroup,
            Long id,
            String menuName,
            String menuPriceName,
            Long menuPrice,
            String menuPhoto,
            String content,
            Long ordering
    ){
        if (menuGroup == null) throw new InvalidParamException("Menu.menuGroup");
        if (menuPrice == null) throw new InvalidParamException("Menu.menuPrice");
        if (ordering == null)  throw new InvalidParamException("Menu.ordering");
        if (StringUtils.isEmpty(menuName))      throw new InvalidParamException("Menu.menuName");
        if (StringUtils.isEmpty(menuPriceName)) throw new InvalidParamException("Menu.menuPriceName");
        if (StringUtils.isEmpty(menuPhoto))     throw new InvalidParamException("Menu.menuPhoto");
        if (StringUtils.isEmpty(content))       throw new InvalidParamException("Menu.content");

        this.menuGroup     = menuGroup;
        this.id            = id;
        this.menuName      = menuName;
        this.menuPriceName = menuPriceName;
        this.menuPrice     = menuPrice;
        this.menuPhoto     = menuPhoto;
        this.content       = content;
        this.ordering      = ordering;
        this.status        = Status.ENABLE;
    }

    // 메뉴 정보 update
    public void update(ShopCommand.MenuRequest menuRequest){
        this.menuName      = menuRequest.getMenuName();
        this.menuPriceName = menuRequest.getMenuPriceName();
        this.menuPrice     = menuRequest.getMenuPrice();
        this.menuPhoto     = menuRequest.getMenuPhoto();
        this.content       = menuRequest.getContent();
        this.ordering      = menuRequest.getOrdering();
    }

    // 메뉴 상태 [ENABLE] 변경
    public void enable() { this.status = Status.ENABLE; }

    // 메뉴 상태 [DISABLE] 변경
    public void disable() { this.status = Status.DISABLE; }
}
