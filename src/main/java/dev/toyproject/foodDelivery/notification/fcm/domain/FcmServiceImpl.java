package dev.toyproject.foodDelivery.notification.fcm.domain;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmServiceImpl implements FcmService {


    /**
     * FCM Push Send
     *
     * @param fcmNotificationRequest
     */
    @Override
    public void sendFcm(FcmNotificationRequest fcmNotificationRequest) {
        try{
            Message message = Message.builder()
                    .setToken(fcmNotificationRequest.getToken())
                    .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                            .setNotification(new WebpushNotification(fcmNotificationRequest.getTitle(),
                                    fcmNotificationRequest.getMessage()))
                            .build())
                    .build();

            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            log.info("Sent message: " + response);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
