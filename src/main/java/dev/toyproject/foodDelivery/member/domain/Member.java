package dev.toyproject.foodDelivery.member.domain;

import dev.toyproject.foodDelivery.AbstracEntity;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.print.DocFlavor;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "member")
public class Member extends AbstracEntity {

    // member Token 구분자
    private static final String MEMBER_PREFIX = "mbr_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   Id;                   // 사용자 시퀀스
    private String memberMail;           // 사용자 이메일
    private String memberToken;          // 사용자 Token 정보
    private String memberPwd;            // 사용자 비밀번호
    private String memberTel;            // 사용자 전화번호
    private String memberNickname;       // 사용자 닉네임
    private String addressCode;          // 사용자 주소코드

    @Enumerated(EnumType.STRING)
    private Status status;               // 사용자 상태

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Member(
            String memberMail,
            String memberToken,
            String memberPwd,
            String memberTel,
            String memberNickname
    ){
        if(StringUtils.isEmpty(memberMail))     throw new InvalidParamException("Member.memberMail");
        if(StringUtils.isEmpty(memberPwd))      throw new InvalidParamException("Member.memberPwd");
        if(StringUtils.isEmpty(memberTel))      throw new InvalidParamException("Member.memberTel");
        if(StringUtils.isEmpty(memberNickname)) throw new InvalidParamException("Member.memberNickname");

        // 사용자 Token 이 빈값이 경우 Token 발행
        if(StringUtils.isEmpty(memberToken)) { this.memberToken = TokenGenerator.randomCharacterWithPrefix(MEMBER_PREFIX); }
        else { this.memberToken    = memberToken; }

        this.memberMail     = memberMail;
        this.memberPwd      = memberPwd;
        this.memberTel      = memberTel;
        this.memberNickname = memberNickname;
        this.status         = Status.ENABLE;
    }

    // 사용자 정보 수정
    public void updateMemberInfo(String memberTel, String memberNickname){
        this.memberTel      = memberTel;
        this.memberNickname = memberNickname;
    }

    // 사용자 비밀번호 변경
    public void updateMemberPassword(String memberPwd){
        this.memberPwd = memberPwd;
    }

    // 사용자 상태 [DISABLE] 변경
    public void disable() { this.status = Status.DISABLE; }

}
