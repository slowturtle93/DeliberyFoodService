package dev.toyproject.foodDelivery.common.util.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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
}
