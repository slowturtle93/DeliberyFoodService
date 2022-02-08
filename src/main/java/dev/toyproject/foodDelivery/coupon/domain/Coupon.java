package dev.toyproject.foodDelivery.coupon.domain;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import dev.toyproject.foodDelivery.member.domain.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "coupon")
public class Coupon extends AbstracEntity {

    // coupon Token 구분자
    private static final String COUPON_PREFIX = "cpn_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponToken;    // 쿠폰 토큰 정보
    private String shopToken;      // 가게 토큰 정보
    private Long discountPrice;    // 할인 금액
    private String endDate;        // 행사 종료일
    private Long expirationPeriod;  // 쿠폰 발급일로 부터 행사기간

    @Enumerated(EnumType.STRING)
    private Status status;  // 쿠폰 상태

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Coupon(
        String shopToken,
        Long discountPrice,
        String endDate,
        Long expirationPeriod
    ){
        if (StringUtils.isEmpty(shopToken)) throw new InvalidParamException("coupon.shopToken");
        if (discountPrice    == null)       throw new InvalidParamException("coupon.discountPrice");
        if (StringUtils.isEmpty(endDate) && expirationPeriod == null) throw new InvalidParamException("coupon.period");

        this.couponToken      = TokenGenerator.randomCharacterWithPrefix(COUPON_PREFIX);
        this.shopToken        = shopToken;
        this.discountPrice    = discountPrice;
        this.endDate          = endDate;
        this.expirationPeriod = expirationPeriod;
        this.status           = Status.DISABLE;
    }

    // 쿠폰 상태 [ENABLE] 변경
    public void enable() { this.status = Status.ENABLE; }
}
