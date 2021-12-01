package dev.toyproject.foodDelivery.common.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomAccessGenerator {

    /**
     * 인증번호 생성 (6자리)
     *
     * @return
     */
    public static String authNumberGenerator() {
        Random random = new Random();

        // 111111 ~ 999999 범위의 숫자를 얻기 위해서 nextInt(888888) + 111111를 사용
        int number = random.nextInt(888888) + 111111;

        return Integer.toString(number);
    }
}
