package dev.toyproject.foodDelivery.common.util.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import lombok.extern.slf4j.Slf4j;
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
     * @param Token
     * @param Key
     * @param hashKey
     * @param orderBasketRequest
     */
    public void setRedisCacheMenuBasket(String Token, String Key, String hashKey, OrderCommand.OrderBasketRequest orderBasketRequest){

        String redisKey = RedisKeyFactory.generateKey(Token, Key);

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
     * @param Key
     * @return
     */
    public List<OrderCommand.OrderBasketRequest> getMenuBasketList(String Token, String Key){
        String redisKey = RedisKeyFactory.generateKey(Token, Key);

        var menuBasket = redisTemplate.opsForHash().entries(redisKey);

        if (menuBasket.size() == 0){
            return null;
        }

        var keys = menuBasket.keySet().toArray(new String[menuBasket.size()]);
        List<OrderCommand.OrderBasketRequest> menuBasketList = new ArrayList<OrderCommand.OrderBasketRequest>();

        for(String hashKey : keys){
            menuBasketList.add(getMenuBasketHashKey(Token, Key, hashKey));
        }

        return menuBasketList;
    }

    /**
     * 장바구니에 적재된 단일 메뉴 조회
     *
     * @param Token
     * @param Key
     * @param hashKey
     * @return
     */
    public OrderCommand.OrderBasketRequest getMenuBasketHashKey(String Token, String Key, String hashKey) {
        String redisKey = RedisKeyFactory.generateKey(Token, Key);

        var orderBasket = redisTemplate.opsForHash().get(redisKey, hashKey);
        OrderCommand.OrderBasketRequest orderBasketInfo = objectMapper.convertValue(orderBasket, OrderCommand.OrderBasketRequest.class);

        return orderBasketInfo;
    }

}
