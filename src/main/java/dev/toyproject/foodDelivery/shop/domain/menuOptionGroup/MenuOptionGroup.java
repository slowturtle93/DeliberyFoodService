package dev.toyproject.foodDelivery.shop.domain.menuOptionGroup;

import com.google.common.collect.Lists;
import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import dev.toyproject.foodDelivery.shop.domain.menuOption.MenuOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "menu_option_group")
public class MenuOptionGroup extends AbstracEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuOptionGroupName;
    private Long ordering;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuOptionGroup", cascade = CascadeType.PERSIST)
    private List<MenuOption> menuOptionList = Lists.newLinkedList();

    @Builder
    public MenuOptionGroup(
            Menu menu,
            Long id,
            String menuOptionGroupName,
            Long ordering
    ){
        if (StringUtils.isEmpty(menuOptionGroupName)) throw new InvalidParamException("menuOptionGroup.menuOptionGroupName");
        if (menu     == null)                         throw new InvalidParamException("menuOptionGroup.menu");
        if (ordering == null)                         throw new InvalidParamException("menuOptionGroup.ordering");

        this.menu                = menu;
        this.id                  = id;
        this.menuOptionGroupName = menuOptionGroupName;
        this.ordering            = ordering;
    }

    //메뉴 옵션 그룹 정보 update
    public void update(ShopCommand.MenuOptionGroupRequest menuOptionGroupRequest){
        this.menuOptionGroupName = menuOptionGroupRequest.getMenuOptionGroupName();
        this.ordering            = menuOptionGroupRequest.getOrdering();
    }
}
