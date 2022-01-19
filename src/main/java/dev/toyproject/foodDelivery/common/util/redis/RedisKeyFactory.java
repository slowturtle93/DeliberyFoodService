package dev.toyproject.foodDelivery.common.util.redis;

public class RedisKeyFactory {

    // 사용자 인증번호
    public static final String MEMBER_AUTH_NUMBER = "MEMBER_AUTH_NUMBER";

    // 메뉴 장바구니
    public static final String MENU_SHOPPING_BASKET = "MENU_SHOPPING_BASKET";

    // FCM TOKEN
    public static final String USER_DEVICE_TOKEN_INFO = "USER_DEVICE_TOKEN_INFO";

    private RedisKeyFactory() {}

    public static String generateKey(String token, String key) {
        return token + ":" + key;
    }
}
