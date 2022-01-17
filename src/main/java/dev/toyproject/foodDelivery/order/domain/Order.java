package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.common.exception.IllegalStatusException;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstracEntity {

    private final static String ORDER_PREFIX = "odr_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderToken;               // 주문 토큰
    private String memberToken;              // 사용자 토큰 정보
    private String shopToken;                // 가게 토큰 정보
    private String paymentMethod;            // 결제 방식
    private Long totalAmount;                // 주문 총 가격
    private Integer ordering;                // 정렬 순서

    @Embedded
    private AddressFragment addressFragment; // 배달 주소

    private ZonedDateTime orderDate;         // 주문 일시

    @Enumerated(EnumType.STRING)
    private Status status;                   // 주문 상태

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderMenu> orderMenuList = Arrays.asList();

    @Builder
    public Order(
            String memberToken,
            String shopToken,
            String paymentMethod,
            Long totalAmount,
            Integer ordering,
            AddressFragment addressFragment
    ){
        if (StringUtils.isEmpty(memberToken))   throw new InvalidParamException("order.memberToken");
        if (StringUtils.isEmpty(shopToken))     throw new InvalidParamException("order.shopToken");
        if (StringUtils.isEmpty(paymentMethod)) throw new InvalidParamException("order.paymentMethod");
        if (totalAmount == null)                throw new InvalidParamException("order.totalAmount");
        if (ordering == null)                   throw new InvalidParamException("order.ordering");
        if (addressFragment == null)            throw new InvalidParamException("order.addressFragment");

        this.orderToken      = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX);
        this.memberToken     = memberToken;
        this.shopToken       = shopToken;
        this.paymentMethod   = paymentMethod;
        this.totalAmount     = totalAmount;
        this.ordering        = ordering;
        this.addressFragment = addressFragment;
        this.orderDate       = ZonedDateTime.now();
        this.status          = Status.INIT;
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품의 가격 = (메뉴 가격 + 메뉴 옵션 가격) * 주문 갯수
     */
    public Long calculateTotalAmount() {
        return orderMenuList.stream()
                .mapToLong(OrderMenu::calculateTotalAmount)
                .sum();
    }

    /**
     *  주문 완료 상태변경 (INIT 인 경우 예외)
     */
    public void orderComplete() {
        if (this.status != Status.INIT) throw new IllegalStatusException();
        this.status = Status.ORDER_COMPLETE;
    }

    /**
     * 주문 상태 [배송준비] 변경
     */
    public void deliveryPrepare() {
        if (this.status != Status.ORDER_COMPLETE) throw new IllegalStatusException();
        this.status = Status.DELIVERY_PREPARE;
    }

    /**
     * 주문 상태 [배송완료] 변경
     */
    public void deliveryComplete() {
        if (this.status != Status.IN_DELIVERY) throw new IllegalStatusException();
        this.status = Status.DELIVERY_COMPLETE;
    }

    /**
     * 주문 상태 변경 준비 확인
     * @return
     */
    public boolean isAlreadyPaymentComplete() {
        return this.status != Status.INIT;
    }
}
