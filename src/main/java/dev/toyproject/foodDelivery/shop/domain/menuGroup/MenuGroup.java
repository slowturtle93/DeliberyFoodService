package dev.toyproject.foodDelivery.shop.domain.menuGroup;

import com.google.common.collect.Lists;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "menu_group")
public class MenuGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuGroupName;
    private Long ordering;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuGroup", cascade = CascadeType.PERSIST)
    private List<Menu> menuList = Lists.newArrayList();

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public MenuGroup(
            Shop shop,
            Long id,
            String menuGroupName,
            Long ordering
    ){
        if (shop == null)                       throw new InvalidParamException("MenuGroup.shop");
        if (StringUtils.isEmpty(menuGroupName)) throw new InvalidParamException("MenuGroup.menuGroupName");
        if (ordering == null)                   throw new InvalidParamException("MenuGroup.ordering");

        this.shop          = shop;
        this.id            = id;
        this.menuGroupName = menuGroupName;
        this.ordering      = ordering;
        this.status        = Status.ENABLE;
    }

    // 메뉴 그룹 정보 update
    public void update(ShopCommand.MenuGroupRequest menuGroupRequest){
        this.menuGroupName = menuGroupRequest.getMenuGroupName();
        this.ordering      = menuGroupRequest.getOrdering();
    }

    // 메뉴 그룹 상태 [ENABLE] 변경
    public void enable() { this.status = Status.ENABLE; }

    // 메뉴 그룹 상태 [DISABLE] 변경
    public void disable() { this.status = Status.DISABLE; }

}
