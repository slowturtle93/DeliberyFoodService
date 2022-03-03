package dev.toyproject.foodDelivery.order.domain.payment;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.IllegalStatusException;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "payment")
public class Payment extends AbstracEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentToken;
    private String orderToken;
    private String paymentType;
    private Long paymentAmount;
    private ZonedDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private Payment.Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        INIT("결제시작"),
        PAYMENT_COMPLETE("결제성공"),
        PAYMENT_REFUND("결제취소");

        private final String description;
    }

    @Builder
    public Payment(
            String paymentToken,
            String orderToken,
            String paymentType,
            Long paymentAmount
    ){
        if(StringUtils.isEmpty(paymentToken)) throw new InvalidParamException("payment.paymentToken");
        if(StringUtils.isEmpty(orderToken))   throw new InvalidParamException("payment.orderToken");
        if(StringUtils.isEmpty(paymentType))  throw new InvalidParamException("payment.paymentType");
        if(paymentAmount == null)             throw new InvalidParamException("payment.paymentAmount");

        this.paymentToken  = paymentToken;
        this.orderToken    = orderToken;
        this.paymentType   = paymentType;
        this.paymentAmount = paymentAmount;
        this.status        = Status.INIT;
        this.paymentDate   = ZonedDateTime.now();
    }

    /**
     * 결제 성공 상태 변경
     */
    public void paymentComplete(){
        if(this.status != Status.INIT) throw new IllegalStatusException();
        this.status = Status.PAYMENT_COMPLETE;
    }

    /**
     * 결제 취소 상태 변경
     */
    public void paymentRefund(){
        if(this.status != Status.PAYMENT_COMPLETE) throw new IllegalStatusException();
        this.status = Status.PAYMENT_REFUND;
    }
}
