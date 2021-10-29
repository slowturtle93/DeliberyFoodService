package dev.toyproject.foodDelivery.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Redis Config
 *
 * @EnableRedisHttpSession
 * 필터를 구현하는 springSessionRepositoryFilter 라는 이름을 가진 스프링빈을 생성시킵니다.
 * 그 필터는 HttpSession 구현체가 스프링 세션에 의해 지원되게 하는데 책임이 있습니다.
 * 이 인스턴스에서는 Spring Session 은 Redis 에 의해 지원됩니다.
 *
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig extends AbstractHttpSessionApplicationInitializer {

    @Value("${spring.redis.host}")
    private String redisHost; // Redis Host (127.0.0.1)

    @Value("${spring.redis.port}")
    private int redisPort;  // Redis Port (6379)

    @Bean(name = "redisSessionConnectionFactory")
    public LettuceConnectionFactory connectionFactory(){
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    // RedisTemplate Bean 설정
    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisSessionConnectionFactory")RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);                 // Redis Connection 설정
        redisTemplate.setKeySerializer(new StringRedisSerializer());                // Redis Key 직렬화 정의
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // Redis Value 직렬화 정의

        return redisTemplate;
    }
}
