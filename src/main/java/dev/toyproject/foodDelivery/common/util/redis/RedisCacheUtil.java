package dev.toyproject.foodDelivery.common.util.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisCacheUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final static int ExpireAuthNumber = 180;

    private final static int ExpireMenuBasket = 180;

    public void setRedisCacheAuthNumber(String Token, String Key, String authNumber){
        String redisKey = RedisKeyFactory.generateKey(Token, Key);
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.watch(redisKey);

        try {
            redisTemplate.multi();
            redisTemplate.opsForValue().set(redisKey, authNumber);
            redisTemplate.expire(redisKey, ExpireAuthNumber, TimeUnit.SECONDS);
            redisTemplate.exec();
        }catch (Exception e) {
            e.getMessage();
        }
    }

    public String getAuthNumber(String Token, String Key){
        return redisTemplate.opsForValue().get(RedisKeyFactory.generateKey(Token, Key)).toString();
    }

    public void removeAuthNumber(String Token, String Key){
        redisTemplate.delete(RedisKeyFactory.generateKey(Token, Key));
    }

    /************************************* MENU BASKET ***********************************/

    /**
     * Redis 장바구니 메뉴 등록
     *
     * @param orderBasketRequest
     * @param hashKey
     */
    public void setRedisCacheMenuBasket(OrderCommand.OrderBasketRequest orderBasketRequest, String hashKey){

        String redisKey = RedisKeyFactory.generateKey(orderBasketRequest.getMemberToken(), RedisKeyFactory.MENU_SHOPPING_BASKET);

        redisTemplate.watch(redisKey);

        try {
            redisTemplate.multi();
            redisTemplate.opsForHash().put(redisKey, hashKey, orderBasketRequest);
            redisTemplate.expire(redisKey, ExpireMenuBasket, TimeUnit.SECONDS);
            redisTemplate.exec();
        }catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 장바구니에 적재된 메뉴 리스트 조회
     *
     * @param Token
     * @return
     */
    public List<OrderInfo.OrderBasketInfo> getMenuBasketList(String Token){
        String redisKey = RedisKeyFactory.generateKey(Token, RedisKeyFactory.MENU_SHOPPING_BASKET);

        var menuBasket = redisTemplate.opsForHash().entries(redisKey);

        if (menuBasket.size() == 0){
            return null;
        }

        var keys = menuBasket.keySet().toArray(new String[menuBasket.size()]);
        List<OrderInfo.OrderBasketInfo> menuBasketList = new ArrayList<OrderInfo.OrderBasketInfo>();

        for(String hashKey : keys){
            menuBasketList.add(getMenuBasketHashKey(Token, hashKey));
        }

        return menuBasketList;
    }

    /**
     * 장바구니에 적재된 단일 메뉴 조회
     *
     * @param Token
     * @param hashKey
     * @return
     */
    public OrderInfo.OrderBasketInfo getMenuBasketHashKey(String Token, String hashKey) {
        String redisKey = RedisKeyFactory.generateKey(Token, RedisKeyFactory.MENU_SHOPPING_BASKET);

        var orderBasket = redisTemplate.opsForHash().get(redisKey, hashKey);
        OrderInfo.OrderBasketInfo orderBasketInfo = objectMapper.convertValue(orderBasket, OrderInfo.OrderBasketInfo.class);

        return orderBasketInfo;
    }

    /**
     * 장바구니에 적재된 특정 메뉴 삭제
     *
     * @param Token
     * @param hashKey
     */
    public void removeMenuBasket(String Token, String hashKey){
        var redisKey = RedisKeyFactory.generateKey(Token, RedisKeyFactory.MENU_SHOPPING_BASKET);
        redisTemplate.opsForHash().delete(redisKey, hashKey);
    }

    /**
     * 장바구니 전체 삭제
     *
     * @param Token
     */
    public void removeMenuBasketAll(String Token){
        var redisKey = RedisKeyFactory.generateKey(Token, RedisKeyFactory.MENU_SHOPPING_BASKET);

        var menuBasket = redisTemplate.opsForHash().entries(redisKey);
        var hashKeys = menuBasket.keySet().toArray(new String[menuBasket.size()]);

        for(String hashKey : hashKeys){
            redisTemplate.opsForHash().delete(redisKey, hashKey);
        }
    }

    /************************************* DEVICE TOKEN ***********************************/

    /**
     * 사용자 device token 정보 저장
     *
     * @param memberToken
     * @param deviceToken
     */
    public void setRedisCacheDeviceToken(String memberToken, String deviceToken){
        String redisKey = RedisKeyFactory.generateKey(memberToken, RedisKeyFactory.USER_DEVICE_TOKEN_INFO);
        redisTemplate.opsForValue().set(redisKey, deviceToken);
    }

    /**
     * 사용자 device token 정보 조회
     *
     * @param memberToken
     * @return
     */
    public String getDeviceTokenInfo(String memberToken) {
        String redisKey = RedisKeyFactory.generateKey(memberToken, RedisKeyFactory.USER_DEVICE_TOKEN_INFO);
        var deviceToken = "";

        try {
            deviceToken = redisTemplate.opsForValue().get(redisKey).toString();
        }catch (Exception e){
            throw new NullPointerException("Device Token Not Found");
        }

        return deviceToken;
    }

    /**
     * 사용자 device token 정보 삭제
     *
     * @param memberToken
     */
    public void removeDeviceToken(String memberToken){
        redisTemplate.delete(RedisKeyFactory.generateKey(memberToken, RedisKeyFactory.USER_DEVICE_TOKEN_INFO));
    }

    /************************************* Member Order PaymentToken ***********************************/

    /**
     * 주문 payment token 정보 저장
     *
     * @param orderToken
     * @param paymentToken
     */
    public void setRedisCacheOrderPaymentToken(String orderToken, String paymentToken){
        String redisKey = RedisKeyFactory.generateKey(orderToken, RedisKeyFactory.USER_ORDER_PAYMENT_TOKEN);
        redisTemplate.opsForValue().set(redisKey, paymentToken);
    }

    /**
     * 주문 payment token 정보 조회
     *
     * @param memberToken
     * @return
     */
    public String getOrderPaymentTokenInfo(String memberToken) {
        String redisKey = RedisKeyFactory.generateKey(memberToken, RedisKeyFactory.USER_ORDER_PAYMENT_TOKEN);
        var paymentToken = "";

        try {
            paymentToken = redisTemplate.opsForValue().get(redisKey).toString();
        }catch (Exception e){
            throw new NullPointerException("Device Token Not Found");
        }

        return paymentToken;
    }

    /**
     * 주문 payment token 정보 삭제
     *
     * @param memberToken
     */
    public void removeOrderPaymentToken(String memberToken){
        redisTemplate.delete(RedisKeyFactory.generateKey(memberToken, RedisKeyFactory.USER_ORDER_PAYMENT_TOKEN));
    }
}
