package dev.toyproject.foodDelivery.member.interfaces;

import dev.toyproject.foodDelivery.common.util.SHA256Util;
import dev.toyproject.foodDelivery.member.domain.Member;
import dev.toyproject.foodDelivery.member.domain.MemberCommand;
import dev.toyproject.foodDelivery.member.domain.MemberInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MemberDto {

    /******************************** request ********************************/

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest{

        @NotEmpty(message = "email 필수값입니다.")
        @Email(message = "email 형식에 맞아야 합니다.")
        private String memberMail;

        @NotEmpty(message = "memberPassword 는 필수값입니다.")
        private String memberPwd;

        @NotEmpty(message = "memberTel 는 필수값입니다.")
        private String memberTel;

        @NotEmpty(message = "memberNickname 는 필수값입니다.")
        private String memberNickname;

        public MemberCommand toCommand(){
            return MemberCommand.builder()
                    .memberMail(memberMail)
                    .memberPwd(SHA256Util.encryptSHA256(memberPwd))
                    .memberTel(memberTel)
                    .memberNickname(memberNickname)
                    .build();
        }
    }

    /******************************** response ********************************/

    @Getter
    @ToString
    public static class response{
        private final String memberMail;
        private final String memberToken;
        private final String memberNickname;
        private final String memberTel;
        private final String addressCode;
        private final Member.Status status;

        public response(MemberInfo memberInfo){
            this.memberMail     = memberInfo.getMemberMail();
            this.memberToken    = memberInfo.getMemberToken();
            this.memberNickname = memberInfo.getMemberNickname();
            this.memberTel      = memberInfo.getMemberTel();
            this.addressCode    = memberInfo.getAddressCode();
            this.status         = memberInfo.getStatus();
        }
    }
}
