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
    private Integer ordering;

    @ManyToOne
    @JoinColumn(name = "order_menu_option_group_id")
    private OrderMenuOptionGroup orderMenuOptionGroup;

    @Builder
    public OrderMenuOption(
            String orderMenuOptionName,
            Long orderMenuOptionPrice,
            Integer ordering,
            OrderMenuOptionGroup orderMenuOptionGroup
    ){
        if (StringUtils.isEmpty(orderMenuOptionName)) throw new InvalidParamException("orderMenuOption.orderMenuOptionName");
        if (orderMenuOptionPrice == null)             throw new InvalidParamException("orderMenuOption.orderMenuOptionPrice");
        if (orderMenuOptionGroup == null)             throw new InvalidParamException("orderMenuOption.orderMenuOptionGroup");
        if (ordering == null)                         throw new InvalidParamException("orderMenuOption.ordering");

        this.orderMenuOptionName        = orderMenuOptionName;
        this.orderMenuOptionPrice       = orderMenuOptionPrice;
        this.orderMenuOptionGroup       = orderMenuOptionGroup;
        this.ordering                   = ordering;
    }
}
