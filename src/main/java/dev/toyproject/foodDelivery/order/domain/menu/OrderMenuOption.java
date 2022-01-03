package dev.toyproject.foodDelivery.order.domain.menu;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_menu_option")
public class OrderMenuOption extends AbstracEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderMenuOptionName;
    private Long orderMenuOptionPrice;

    @ManyToOne
    @JoinColumn(name = "order_menu_id")
    private OrderMenu orderMenu;

    @Builder
    public OrderMenuOption(
            String orderMenuOptionName,
            Long orderMenuOptionPrice,
            OrderMenu orderMenu
    ){
        if (StringUtils.isEmpty(orderMenuOptionName)) throw new InvalidParamException("orderMenuOption.orderMenuOptionName");
        if (orderMenuOptionPrice == null)             throw new InvalidParamException("orderMenuOption.orderMenuOptionPrice");
        if (orderMenu == null)                        throw new InvalidParamException("orderMenuOption.orderMenu");

        this.orderMenuOptionName        = orderMenuOptionName;
        this.orderMenuOptionPrice       = orderMenuOptionPrice;
        this.orderMenu                  = orderMenu;
    }
}
