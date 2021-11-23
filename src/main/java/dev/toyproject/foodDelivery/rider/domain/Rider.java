package dev.toyproject.foodDelivery.rider.domain;

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
@Table(name = "rider")
public class Rider extends AbstracEntity {

    private final static String RIDER_PREFIX = "rid_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    Id;            // 라이더 시퀀스
    private String riderLoginId;   // 라이더 로그인 아이디
    private String riderToken;     // 라이더 토큰
    private String riderPwd;       // 라이더 비밀번호
    private String riderName;      // 라이더 이름
    private String riderTel;       // 라이더 전화번호
    private String residence;      // 라이더 거주지
    private String deliveryRegion; // 배달 지역
    private String deliveryMethod; // 배달 방법

    @Enumerated(EnumType.STRING)
    private Rider.Status status; // 라이더 상태

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Rider(
            String riderLoginId,
            String riderPwd,
            String riderToken,
            String riderName,
            String riderTel,
            String residence,
            String deliveryRegion,
            String deliveryMethod
    ){
        if(StringUtils.isEmpty(riderLoginId))   throw new InvalidParamException("Rider.riderLoginId");
        if(StringUtils.isEmpty(riderPwd))       throw new InvalidParamException("Rider.riderPwd");
        if(StringUtils.isEmpty(riderName))      throw new InvalidParamException("Rider.riderName");
        if(StringUtils.isEmpty(riderTel))       throw new InvalidParamException("Rider.riderTel");
        if(StringUtils.isEmpty(residence))      throw new InvalidParamException("Rider.residence");
        if(StringUtils.isEmpty(deliveryRegion)) throw new InvalidParamException("Rider.deliveryRegion");
        if(StringUtils.isEmpty(deliveryMethod)) throw new InvalidParamException("Rider.deliveryMethod");

        if(StringUtils.isEmpty(riderToken)) { this.riderToken = TokenGenerator.randomCharacterWithPrefix(RIDER_PREFIX); }
        else { this.riderToken    = riderToken; }

        this.riderLoginId   = riderLoginId;
        this.riderPwd       = riderPwd;
        this.riderName      = riderName;
        this.riderTel       = riderTel;
        this.residence      = residence;
        this.deliveryRegion = deliveryRegion;
        this.deliveryMethod = deliveryMethod;
        this.status         = Rider.Status.ENABLE;
    }

    // 라이더 정보 수정
    public void updateRiderInfo(RiderCommand command){
        if(StringUtils.isEmpty(command.getRiderName()))      throw new InvalidParamException("Rider.riderName");
        if(StringUtils.isEmpty(command.getRiderTel()))       throw new InvalidParamException("Rider.riderTel");
        if(StringUtils.isEmpty(command.getResidence()))      throw new InvalidParamException("Rider.residence");
        if(StringUtils.isEmpty(command.getDeliveryRegion())) throw new InvalidParamException("Rider.deliveryRegion");
        if(StringUtils.isEmpty(command.getDeliveryMethod())) throw new InvalidParamException("Rider.deliveryMethod");

        this.riderName      = command.getRiderName();
        this.riderTel       = command.getRiderTel();
        this.residence      = command.getResidence();
        this.deliveryRegion = command.getDeliveryRegion();
        this.deliveryMethod = command.getDeliveryMethod();
    }

    // 사용자 비밀번호 변경
    public void updateRiderPassword(String riderPwd){
        this.riderPwd = riderPwd;
    }

    // 라이더 상태 [ENABLE] 변경
    public void enable() { this.status = Rider.Status.ENABLE; }

    // 라이더 상태 [DISABLE] 변경
    public void disable() { this.status = Status.DISABLE; }
}
