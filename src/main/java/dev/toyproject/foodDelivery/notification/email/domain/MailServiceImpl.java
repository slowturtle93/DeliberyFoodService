package dev.toyproject.foodDelivery.notification.email.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "gkrthdz@gmail.com";

    /**
     * Mail 인증번호 발송
     *
     * @param mailRequest
     */
    @Override
    public void sendMail(MailSendRequest mailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getAddress());
        message.setSubject(mailRequest.getTitle());
        message.setText(mailRequest.getMessage());

        mailSender.send(message);

    }
}
