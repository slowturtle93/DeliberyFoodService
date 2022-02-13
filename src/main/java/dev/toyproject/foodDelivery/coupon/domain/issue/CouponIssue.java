package dev.toyproject.foodDelivery.coupon.domain.issue;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "coupon_issue")
public class CouponIssue extends AbstracEntity {

    // coupon issue Token 구분자
    private static final String COUPON_ISSUE_PREFIX = "cis_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couponIssueToken;
    private String couponToken;
    private String memberToken;

    @Enumerated(EnumType.STRING)
    private Status status;  // 쿠폰 상태

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        INIT("쿠폰발행"),
        USED("사용완료");

        private final String description;
    }

    @Builder
    public CouponIssue(
            String couponToken,
            String memberToken
    ){
        if (StringUtils.isEmpty(couponToken)) throw new InvalidParamException("couponIssue.couponToken");
        if (StringUtils.isEmpty(memberToken)) throw new InvalidParamException("couponIssue.memberToken");

        this.couponIssueToken = TokenGenerator.randomCharacterWithPrefix(COUPON_ISSUE_PREFIX);
        this.couponToken = couponToken;
        this.memberToken = memberToken;
        this.status      = Status.INIT;
    }
}
