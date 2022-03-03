package dev.toyproject.foodDelivery.owner.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class OwnerCommand {

    private String ownerLoginId; // 사장 아이디
    private String ownerMail;    // 사장 이메일
    private String ownerToken;   // 사장 Token 정보
    private String ownerPwd;     // 사장 비밀번호
    private String ownerTel;     // 사장 전화번호
    private String ownerBirth;   // 사장 생년월일
    private String ownerName;    // 사장 이름
    private String deviceToken;  // 사장님 device Token


    public Owner toEntity() {
        return Owner.builder()
                .ownerLoginId(ownerLoginId)
                .ownerMail(ownerMail)
                .ownerToken(ownerToken)
                .ownerPwd(ownerPwd)
                .ownerTel(ownerTel)
                .ownerBirth(ownerBirth)
                .ownerName(ownerName)
                .build();
    }
}

