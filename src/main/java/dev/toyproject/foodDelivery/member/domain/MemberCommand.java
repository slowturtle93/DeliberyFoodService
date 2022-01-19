package dev.toyproject.foodDelivery.member.domain;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 *  Command - Create, Update, Delete 시 사용되는 객체
 */
public class MemberCommand {

    @Getter
    @Builder
    @ToString
    public static class Main{
        private String memberMail;     // 사용자 이메일
        private String memberToken;    // 사용자 Token
        private String memberPwd;      // 사용자 비밀번호
        private String memberTel;      // 사용자 전화번호
        private String memberNickname; // 사용자 닉네임
        private String deviceToken;

        public Member toEntity() {
            return Member.builder()
                    .memberMail(memberMail)
                    .memberToken(memberToken)
                    .memberPwd(memberPwd)
                    .memberTel(memberTel)
                    .memberNickname(memberNickname)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class Address{
        private String memberToken;
        private String detail;
        private AddressFragment addressFragment;

        public MemberAddress toEntity(){
            return MemberAddress.builder()
                    .memberToken(memberToken)
                    .detail(detail)
                    .addressFragment(addressFragment)
                    .build();
        }
    }
}
