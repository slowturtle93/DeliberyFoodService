package dev.toyproject.foodDelivery.shop.domain.menuOption;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.menuOptionGroup.MenuOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "menu_option")
public class MenuOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuOptionName;
    private Long menuOptionPrice;
    private Long ordering;

    @ManyToOne
    @JoinColumn(name = "menu_option_group_id")
    private MenuOptionGroup menuOptionGroup;

    @Builder
    public MenuOption(
            MenuOptionGroup menuOptionGroup,
            Long id,
            String menuOptionName,
            Long menuOptionPrice,
            Long ordering
    ){
        if (StringUtils.isEmpty(menuOptionName)) throw new InvalidParamException("menuOption.menuOptionName");
        if (menuOptionGroup == null) throw new InvalidParamException("menuOption.menuOptionGroup");
        if (menuOptionPrice == null) throw new InvalidParamException("menuOption.menuOptionPrice");
        if (ordering == null)        throw new InvalidParamException("menuOption.ordering");

        this.menuOptionGroup = menuOptionGroup;
        this.id              = id;
        this.menuOptionName  = menuOptionName;
        this.menuOptionPrice = menuOptionPrice;
        this.ordering        = ordering;
    }

    // 메뉴 옵션 정보 update
    public void update(ShopCommand.MenuOptionRequest menuOptionRequest){
        this.menuOptionName  = menuOptionRequest.getMenuOptionName();
        this.menuOptionPrice = menuOptionRequest.getMenuOptionPrice();
        this.ordering        = menuOptionRequest.getOrdering();
    }
}
