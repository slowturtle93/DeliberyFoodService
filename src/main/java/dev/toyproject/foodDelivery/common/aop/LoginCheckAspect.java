package dev.toyproject.foodDelivery.common.aop;

import dev.toyproject.foodDelivery.common.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Aspect
@Component
public class LoginCheckAspect {

    /**
     * LoginCheck Annotation 붙은 메서드에 한해 해당 메서드 실행 전 Member Type 확인
     *
     * @param jp
     * @throws Throwable
     */
    @Before("@annotation(dev.practice.toyproject.foodDelivery.common.aop.LoginCheck)")
    public void memberLoginCheck(JoinPoint jp) throws Throwable {
        log.debug("AOP - Member Login Check Started");

        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String memberToken = SessionUtil.getLoginMemberId(session);

        if (memberToken == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "NO_LOGIN") {};
        }
    }
}
