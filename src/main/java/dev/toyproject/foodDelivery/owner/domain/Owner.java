package dev.toyproject.foodDelivery.owner.domain;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "OWNER")
public class Owner extends AbstractEntity {

    // owner Token 구분자
    private static final String OWNER_PREFIX = "owr_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   Id;                  // 사장 시퀀스
    private String ownerLoginId;        // 사장 아이디
    private String ownerMail;           // 사장 이메일
    private String ownerToken;          // 사장 Token 정보
    private String ownerPwd;            // 사장 비밀번호
    private String ownerTel;            // 사장 전화번호
    private String ownerBirth;          // 사장 생년월일
    private String ownerName;           // 사장 이름

    @Enumerated(EnumType.STRING)
    private Status status;               // 사장 상태

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Owner(
            String ownerLoginId,
            String ownerToken,
            String ownerPwd,
            String ownerTel,
            String ownerBirth,
            String ownerName,
            String ownerMail
    ){
        if(ownerLoginId     == null) throw new InvalidParamException("owner.ownerLoginId");
        if(ownerPwd         == null) throw new InvalidParamException("owner.ownerPwd");
        if(ownerTel         == null) throw new InvalidParamException("owner.ownerTel");
        if(ownerBirth       == null) throw new InvalidParamException("owner.ownerBirth");
        if(ownerName        == null) throw new InvalidParamException("owner.ownerName");

        // 사장 Token 이 빈값이 경우 Token 발행
        if(StringUtils.isEmpty(ownerToken)) { this.ownerToken = TokenGenerator.randomCharacterWithPrefix(OWNER_PREFIX); }
        else { this.ownerToken    = ownerToken; }

        this.ownerLoginId   = ownerLoginId;
        this.ownerPwd       = ownerPwd;
        this.ownerTel       = ownerTel;
        this.ownerBirth     = ownerBirth;
        this.ownerName      = ownerName;
        this.ownerMail      = ownerMail;
        this.status         = Status.ENABLE;
    }

    // 사장 정보 수정
    public void updateOwnerInfo(String ownerTel, String ownerBirth, String ownerMail){
        this.ownerTel       = ownerTel;
        this.ownerBirth     = ownerBirth;
        this.ownerMail      = ownerMail;
    }

    // 사장 비밀번호 변경
    public void updateOwnerPassword(String ownerPwd){
        this.ownerPwd = ownerPwd;
    }

    // 사장 상태 [ENABLE] 변경
    public void enable() { this.status = Status.ENABLE; }

    // 사장 상태 [DISABLE] 변경
    public void disable() { this.status = Status.DISABLE; }
}

