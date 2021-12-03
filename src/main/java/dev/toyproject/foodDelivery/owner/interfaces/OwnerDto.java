package dev.toyproject.foodDelivery.owner.interfaces;

import dev.toyproject.foodDelivery.common.util.SHA256Util;
import dev.toyproject.foodDelivery.owner.domain.Owner;
import dev.toyproject.foodDelivery.owner.domain.OwnerCommand;
import dev.toyproject.foodDelivery.owner.domain.OwnerInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class OwnerDto {

    /******************************** request ********************************/

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest{

        @NotEmpty(message = "ownerLoginId 필수값입니다.")
        private String ownerLoginId;

        @NotEmpty(message = "ownerPassword 는 필수값입니다.")
        private String ownerPwd;

        @NotEmpty(message = "email 필수값입니다.")
        @Email(message = "email 형식에 맞아야 합니다.")
        private String ownerMail;

        @NotEmpty(message = "ownerTel 는 필수값입니다.")
        private String ownerTel;

        @NotEmpty(message = "ownerName 는 필수값입니다.")
        private String ownerName;

        @NotEmpty(message = "ownerBirth 는 필수값입니다.")
        private String ownerBirth;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerLoginId(ownerLoginId)
                    .ownerPwd(SHA256Util.encryptSHA256(ownerPwd))
                    .ownerMail(ownerMail)
                    .ownerTel(ownerTel)
                    .ownerName(ownerName)
                    .ownerBirth(ownerBirth)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LoginRequest{

        @NotEmpty(message = "ownerLoginId 필수값입니다.")
        private String ownerLoginId;

        @NotEmpty(message = "ownerPassword 는 필수값입니다.")
        private String ownerPwd;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerLoginId(ownerLoginId)
                    .ownerPwd(SHA256Util.encryptSHA256(ownerPwd))
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class UpdateRequest{

        @NotEmpty(message = "ownerToken 는 필수값입니다.")
        private String ownerToken;

        @NotEmpty(message = "ownerTel 는 필수값입니다.")
        private String ownerTel;

        @NotEmpty(message = "ownerName 는 필수값입니다.")
        private String ownerName;

        @NotEmpty(message = "ownerMail 는 필수값입니다.")
        private String ownerMail;

        @NotEmpty(message = "ownerBirth 는 필수값입니다.")
        private String ownerBirth;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerToken(ownerToken)
                    .ownerMail(ownerMail)
                    .ownerTel(ownerTel)
                    .ownerName(ownerName)
                    .ownerBirth(ownerBirth)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class UpdatePasswordRequest{
        @NotEmpty(message = "ownerToken 는 필수값입니다.")
        private String ownerToken;

        @NotEmpty(message = "beforePassword 는 필수값입니다.")
        private String beforePassword;

        @NotEmpty(message = "afterPassword 는 필수값입니다.")
        private String afterPassword;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerToken(ownerToken)
                    .ownerPwd(SHA256Util.encryptSHA256(beforePassword))
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ChangeOwnerRequest{
        private String ownerToken;
    }

    @Getter
    @Setter
    @ToString
    public static class AuthCheckRequest{
        private String ownerLoginId;
        private String ownerTel;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerLoginId(ownerLoginId)
                    .ownerTel(ownerTel)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class OwnerInfoToSmsRequest{
        private String ownerToken;
        private String ownerTel;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerToken(ownerToken)
                    .ownerTel(ownerTel)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class OwnerInfoToMailRequest{
        private String ownerToken;
        private String ownerMail;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerToken(ownerToken)
                    .ownerMail(ownerMail)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class NewPasswordUpdateRequest{
        private String ownerToken;
        private String ownerPwd;

        public OwnerCommand toCommand(){
            return OwnerCommand.builder()
                    .ownerToken(ownerToken)
                    .ownerPwd(SHA256Util.encryptSHA256(ownerPwd))
                    .build();
        }
    }

    /******************************** response ********************************/

    @Getter
    @ToString
    public static class response{
        private final String ownerLoginId;
        private final String ownerMail;
        private final String ownerToken;
        private final String ownerName;
        private final String ownerTel;
        private final String ownerBirth;
        private final Owner.Status status;

        public response(OwnerInfo ownerInfo){
            this.ownerLoginId   = ownerInfo.getOwnerLoginId();
            this.ownerMail      = ownerInfo.getOwnerMail();
            this.ownerToken     = ownerInfo.getOwnerToken();
            this.ownerName      = ownerInfo.getOwnerName();
            this.ownerTel       = ownerInfo.getOwnerTel();
            this.ownerBirth     = ownerInfo.getOwnerBirth();
            this.status         = ownerInfo.getStatus();
        }
    }
}
