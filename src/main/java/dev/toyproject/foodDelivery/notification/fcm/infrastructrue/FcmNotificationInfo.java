package dev.toyproject.foodDelivery.notification.fcm.infrastructrue;

public class FcmNotificationInfo {

    private FcmNotificationInfo() {}

    // 주문 접수 FCM TITLE & MESSAGE
    public static final String FCM_ORDER_INIT_TITLE = "[주문 접수 완료]";
    public static final String FCM_ORDER_INIT_MESSAGE = "고객님의 주문이 정상적으로 접수 되었습니다.";
}
