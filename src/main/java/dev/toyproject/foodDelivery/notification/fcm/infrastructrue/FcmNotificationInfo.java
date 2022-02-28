package dev.toyproject.foodDelivery.notification.fcm.infrastructrue;

public class FcmNotificationInfo {

    private FcmNotificationInfo() {}

    // 주문 접수 FCM TITLE & MESSAGE
    public static final String FCM_ORDER_INIT_TITLE   = "[주문 접수 완료]";
    public static final String FCM_ORDER_INIT_MESSAGE = "고객님의 주문이 정상적으로 접수 되었습니다.";

    // 사장님 주문 요청 확인 FCM TITLE & MESSAGE
    public static final String FCM_OWNER_ORDER_CONFIRM_TITLE   = "[주문 확인 요청]";
    public static final String FCM_OWNER_ORDER_CONFIRM_MESSAGE = "고객님이 음식을 주문하였습니다. 주문 내역을 확인해주세요.";

    // 사장님 주문 승인 FCM TITLE & MESSAGE
    public static final String FCM_OWNER_ORDER_APPROVAL_TITLE   = "[주문 승인]";
    public static final String FCM_OWNER_ORDER_APPROVAL_MESSAGE = "주문이 접수되었습니다. 주문하신 음식을 곧장 배달해드리겠습니다.";

    // 라이더 배달 pickup 완료 FCM TITLE & MESSAGE
    public static final String FCM_RIDER_ORDER_IN_DELIVERY_TITLE   = "[배달 픽업 완료]";
    public static final String FCM_RIDER_ORDER_IN_DELIVERY_MESSAGE = "주문하신 음식을 안전하게 배달해 드리겠습니다.";
}
