package dev.toyproject.foodDelivery.order.domain.menu;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_menu")
public class OrderMenu extends AbstracEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderMenuName;
    private Long orderMenuCount;
    private Long orderMenuPrice;
    private Integer ordering;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMenu", cascade = CascadeType.PERSIST)
    private List<OrderMenuOptionGroup> orderMenuOptionGroupList = Arrays.asList();

    @Builder
    public OrderMenu(
            String orderMenuName,
            Long orderMenuCount,
            Long orderMenuPrice,
            Integer ordering,
            Order order
    ){
        if (StringUtils.isEmpty(orderMenuName)) throw new InvalidParamException("orderMenu.orderMenuName");
        if (orderMenuCount == null)             throw new InvalidParamException("orderMenu.orderMenuCount");
        if (orderMenuPrice == null)             throw new InvalidParamException("orderMenu.orderMenuPrice");
        if (ordering == null)                   throw new InvalidParamException("orderMenu.ordering");
        if (order == null)                      throw new InvalidParamException("orderMenu.order");

        this.orderMenuName  = orderMenuName;
        this.orderMenuCount = orderMenuCount;
        this.orderMenuPrice = orderMenuPrice;
        this.ordering       = ordering;
        this.order          = order;
    }

    // 주문 상품 가격 계산(상품의 옵션 가격 포함)
    // (상품 가격 + 상품 옵션 가격) * 주문수량
    public Long calculateTotalAmount(){
        var itemOptionTotalAmount = orderMenuOptionList.stream()
                .mapToLong(OrderMenuOption::getOrderMenuOptionPrice)
                .sum();
        return (orderMenuPrice + itemOptionTotalAmount) * orderMenuCount;
    }
}
