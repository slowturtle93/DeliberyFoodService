package dev.toyproject.foodDelivery.order.domain.menu;

import com.google.common.collect.Lists;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
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
@Table(name = "order_menu_option_group")
public class OrderMenuOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_menu_id")
    private OrderMenu orderMenu;
    private String    menuOptionGroupName;

    // 주문 상품의 옵션 리스트
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMenuOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderMenuOption> orderMenuOptionList = Lists.newArrayList();

    @Builder
    public OrderMenuOptionGroup(
            OrderMenu orderMenu,
            String menuOptionGroupName
    ) {
        if (orderMenu == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(menuOptionGroupName)) throw new InvalidParamException();

        this.orderMenu = orderMenu;
        this.menuOptionGroupName = menuOptionGroupName;
    }
}
