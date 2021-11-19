package dev.toyproject.foodDelivery.owner.application;

import dev.toyproject.foodDelivery.common.util.SessionUtil;
import dev.toyproject.foodDelivery.owner.domain.OwnerCommand;
import dev.toyproject.foodDelivery.owner.domain.OwnerInfo;
import dev.toyproject.foodDelivery.owner.domain.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerFacade {

    private final OwnerService ownerService;

    /**
     * 회원가입 진행
     *
     * @param command
     * @return
     */
    public OwnerInfo registerOwner(OwnerCommand command){
        var ownerInfo = ownerService.registerOwner(command); // 사장 등록
        return ownerInfo;
    }

    /**
     * 사장님 로그인 아이디 중복 확인
     *
     * @param ownerLoginId
     */
    public void duplicateOwnerLoginId(String ownerLoginId){
        ownerService.duplicateOwnerLoginId(ownerLoginId); // 로그인아이디 중복 확인
    }

    /**
     * 사장님 로그인 진행
     *
     * @param command
     * @param session
     * @return
     */
    public OwnerInfo loginOwner(OwnerCommand command, HttpSession session){
        var loginOwnerInfo = ownerService.loginOwnerInfo(command.getOwnerLoginId(), command.getOwnerPwd()); // 로그인 정보 확인
        SessionUtil.setLoginOwnerToken(session, loginOwnerInfo.getOwnerToken()); // session 에 사장 Token 정보 저장
        return loginOwnerInfo;
    }

    /**
     * 사장님 로그아웃 진행
     *
     * @param session
     */
    public void logoutOwner(HttpSession session){
        SessionUtil.removeLogoutOwner(session); // session 에 사장 Token 정보 삭제
    }
}
