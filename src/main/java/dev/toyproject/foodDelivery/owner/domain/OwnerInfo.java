package dev.toyproject.foodDelivery.owner.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OwnerInfo {

    private String ownerLoginId; // 사장 아이디
    private String ownerMail;    // 사장 이메일
    private String ownerToken;   // 사장 Token 정보
    private String ownerPwd;     // 사장 비밀번호
    private String ownerTel;     // 사장 전화번호
    private String ownerBirth;   // 사장 생년월일
    private String ownerName;    // 사장 이름
    private Owner.Status status; // 사용자 상태

    @Builder
    public OwnerInfo(Owner owner){
        this.ownerLoginId = owner.getOwnerLoginId();
        this.ownerMail    = owner.getOwnerMail();
        this.ownerToken   = owner.getOwnerToken();
        this.ownerPwd     = owner.getOwnerPwd();
        this.ownerTel     = owner.getOwnerTel();
        this.ownerBirth   = owner.getOwnerBirth();
        this.ownerName    = owner.getOwnerName();
        this.status       = owner.getStatus();
    }
}
