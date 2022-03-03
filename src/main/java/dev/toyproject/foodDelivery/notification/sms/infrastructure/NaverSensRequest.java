package dev.toyproject.foodDelivery.notification.sms.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class NaverSensRequest {

    private String from;

    private String type;

    private String contentType;

    private String countryCode;

    private String content;

    private List<Message> messages = new ArrayList<Message>();

    @Builder
    public NaverSensRequest(NaverSensDefalutRequest request, Message message, String content){
        this.from        = request.getFrom();
        this.type        = request.getType();
        this.contentType = request.getContentType();
        this.countryCode = request.getCountryCode();
        this.content     = content;
        this.messages.add(message);
    }

    @Builder
    private static class Message{
        public String content;
        public String to;
    }

    public static NaverSensRequest toRequest(NaverSensDefalutRequest request, Message message, String content){
        return NaverSensRequest.builder()
                .request(request)
                .content(content)
                .message(message)
                .build();
    }

    public static Message toMessage(String content, String to){
        return Message.builder()
                .content(content)
                .to(to)
                .build();
    }

    /**************************************************************************************************/

    @Getter
    @Builder
    public static class makeMessageInfo{
        private String phoneNumber;
        private String messageContent;
        private String content;
    }

    public static makeMessageInfo toAuthNumberInfo(String phoneNumber, String authNumber){
        return makeMessageInfo.builder()
                .phoneNumber(phoneNumber)
                .content("[인증번호 발송]")
                .messageContent("인증번호 [" + authNumber + "]를 입력해 주세요.")
                .build();
    }

    public static makeMessageInfo toPwdLinkInfo(String phoneNumber, String ownerToken){
        return makeMessageInfo.builder()
                .phoneNumber(phoneNumber)
                .content("[비밀번호 변경 링크 발송]")
                .messageContent("비밀번호 변경 링크는 https://3.37.42.105:8082/new/password?ownerToken=" + ownerToken + " 입니다.")
                .build();
    }

}

