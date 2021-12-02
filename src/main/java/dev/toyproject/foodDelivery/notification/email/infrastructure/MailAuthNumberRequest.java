package dev.toyproject.foodDelivery.notification.email.infrastructure;

import dev.toyproject.foodDelivery.notification.email.domain.MailSendRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MailAuthNumberRequest extends MailSendRequest {

    @Builder
    public MailAuthNumberRequest(String address, String authNumber){
        super.address = address;
        super.title   = messageSubject();
        super.message = messageContent(authNumber);
    }

    private String messageSubject(){
        return "인증번호 발송";
    }

    private String messageContent(String authNumber){
        return "인증번호 [" + authNumber + "]를 입력해주세요.";
    }
}
