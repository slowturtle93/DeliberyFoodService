package dev.toyproject.foodDelivery.notification.sms.infrastructure;

import dev.toyproject.foodDelivery.notification.sms.domain.NaverSensKeyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSensKeyFactoryImpl implements NaverSensKeyFactory {

    @Override
    public String makeSignature(String serviceId, String timeStamp, String accessKey, String secretKey) {
        try{
            var space   = " "; // one space
            var newLine = "\n"; // new line
            var method  = "POST"; // method
            var url     = "/sms/v2/services/ncp:sms:kr:26752:fooddelpjt/messages";

            String message = new StringBuilder()
                    .append(method)
                    .append(space)
                    .append(url)
                    .append(newLine)
                    .append(timeStamp)
                    .append(newLine)
                    .append(accessKey)
                    .toString();

            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            String encodeBase64String = Base64.encodeBase64String(rawHmac);

            return encodeBase64String;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
