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

        public MemberCommand.Main toCommand(){
            return MemberCommand.Main.builder()
                    .memberMail(memberMail)
                    .memberPwd(SHA256Util.encryptSHA256(memberPwd))
                    .memberTel(memberTel)
                    .memberNickname(memberNickname)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LoginRequest{

        @NotEmpty(message = "email 필수값입니다.")
        @Email(message = "email 형식에 맞아야 합니다.")
        private String memberMail;

        @NotEmpty(message = "memberPassword 는 필수값입니다.")
        private String memberPwd;

        public MemberCommand.Main toCommand(){
            return MemberCommand.Main.builder()
                    .memberMail(memberMail)
                    .memberPwd(SHA256Util.encryptSHA256(memberPwd))
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class UpdateRequest{
        @NotEmpty(message = "memberToken 는 필수값입니다.")
        private String memberToken;

        @NotEmpty(message = "memberTel 는 필수값입니다.")
        private String memberTel;

        @NotEmpty(message = "memberNickname 는 필수값입니다.")
        private String memberNickname;

        public MemberCommand.Main toCommand(){
            return MemberCommand.Main.builder()
                    .memberToken(memberToken)
                    .memberTel(memberTel)
                    .memberNickname(memberNickname)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class UpdatePasswordRequest{
        @NotEmpty(message = "memberToken 는 필수값입니다.")
        private String memberToken;

        @NotEmpty(message = "beforePassword 는 필수값입니다.")
        private String beforePassword;

        @NotEmpty(message = "afterPassword 는 필수값입니다.")
        private String afterPassword;

        public MemberCommand.Main toCommand(){
            return MemberCommand.Main.builder()
                    .memberToken(memberToken)
                    .memberPwd(SHA256Util.encryptSHA256(beforePassword))
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ChangeMemberRequest{
        private String memberToken;
    }

    @Getter
    @Setter
    @ToString
    public static class authCheckRequest{
        private String memberToken;
        private String memberMail;
        private String memberTel;

        public MemberCommand.Main toCommand(){
            return MemberCommand.Main.builder()
                    .memberToken(memberToken)
                    .memberMail(memberMail)
                    .memberTel(memberTel)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class authCheckNumberRequest{
        private String memberToken;
        private String authNumber;
    }

    @Getter
    @Setter
    @ToString
    public static class NewPasswordRequest{
        private String memberToken;
        private String memberPwd;

        public MemberCommand.Main toCommand(){
            return MemberCommand.Main.builder()
                    .memberToken(memberToken)
                    .memberPwd(SHA256Util.encryptSHA256(memberPwd))
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

        public response(MemberInfo.Main memberInfo){
            this.memberMail     = memberInfo.getMemberMail();
            this.memberToken    = memberInfo.getMemberToken();
            this.memberNickname = memberInfo.getMemberNickname();
            this.memberTel      = memberInfo.getMemberTel();
            this.addressCode    = memberInfo.getAddressCode();
            this.status         = memberInfo.getStatus();
        }
    }
}
