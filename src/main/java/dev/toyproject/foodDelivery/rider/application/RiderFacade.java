package dev.toyproject.foodDelivery.rider.application;

import dev.toyproject.foodDelivery.common.util.SessionUtil;
import dev.toyproject.foodDelivery.rider.domain.RiderCommand;
import dev.toyproject.foodDelivery.rider.domain.RiderInfo;
import dev.toyproject.foodDelivery.rider.domain.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderFacade {

    private final RiderService riderService;

    /**
     * 라이더 등록
     *
     * @param command
     * @return
     */
    public RiderInfo registerRider(RiderCommand command){
        var riderInfo = riderService.registerRider(command);
        return riderInfo;
    }

    /**
     * 로그인 아이디 중복 확인
     *
     * @param loginId
     */
    public void duplicateLoginId(String loginId){
        riderService.duplicateLoginId(loginId);
    }

    /**
     * 라이더 로그인 진행
     *
     * @param command
     * @param session
     * @return
     */
    public RiderInfo loginRider(RiderCommand command, HttpSession session){
        var riderInfo = riderService.loginRiderInfo(command.getRiderLoginId(), command.getRiderPwd()); // 로그인 정보 확인
        SessionUtil.setLoginRiderToken(session, riderInfo.getRiderToken()); // session 에 라이더 Token 정보 저장
        return riderInfo;
    }

    /**
     * 라이더 로그아웃 진행
     *
     * @param session
     */
    public void logoutRider(HttpSession session){
        SessionUtil.removeLogoutRider(session); // session 에 라이더 Token 정보 삭제
    }
}
