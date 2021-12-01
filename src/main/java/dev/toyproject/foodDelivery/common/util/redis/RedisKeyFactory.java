package dev.toyproject.foodDelivery.common.util.redis;

public class RedisKeyFactory {

    public static final String MEMBER_AUTH_NUMBER = "MEMBER_AUTH_NUMBER";

    private RedisKeyFactory() {}

    public static String generateKey(String token, String key) {
        return token + ":" + key;
    }
}
