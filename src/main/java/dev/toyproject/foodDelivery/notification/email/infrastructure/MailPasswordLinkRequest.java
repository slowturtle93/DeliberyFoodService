package dev.toyproject.foodDelivery.notification.email.infrastructure;

import dev.toyproject.foodDelivery.notification.email.domain.MailSendRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MailPasswordLinkRequest extends MailSendRequest {

    @Builder
    public MailPasswordLinkRequest(String address, String ownerToken){
        super.address = address;
        super.title   = messageSubject();
        super.message = messageContent(ownerToken);
    }

    private String messageSubject(){
        return "비밀번호 변경링크 발송";
    }

    private String messageContent(String ownerToken){
        return "비밀번호 변경 링크는 https://3.37.42.105:8082/new/password?ownerToken=" + ownerToken + " 입니다.";
    }
}
