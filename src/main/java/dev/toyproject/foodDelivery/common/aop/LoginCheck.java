package dev.toyproject.foodDelivery.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LoginCheck 어노테이션 생성
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {

    UserType type();

    // 로그인 진행하는 Type 구분
    public static enum UserType {
        MEMBER
    }
}
