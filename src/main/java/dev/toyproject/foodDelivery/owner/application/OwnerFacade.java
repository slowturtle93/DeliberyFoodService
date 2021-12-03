package dev.toyproject.foodDelivery.owner.application;

import dev.toyproject.foodDelivery.common.util.redis.SessionUtil;
import dev.toyproject.foodDelivery.notification.email.domain.MailSendRequest;
import dev.toyproject.foodDelivery.notification.email.domain.MailService;
import dev.toyproject.foodDelivery.notification.email.infrastructure.MailPasswordLinkRequest;
import dev.toyproject.foodDelivery.notification.sms.domain.NaverSensService;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.NaverSensRequest;
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

    private final NaverSensService naverSensService;
    private final MailService mailService;

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

    /**
     * 사장님 정보 수정
     *
     * @param command
     * @return
     */
    public OwnerInfo updateOwner(OwnerCommand command){
        var ownerInfo = ownerService.updateOwner(command); // 사장님 정보 수정
        return ownerInfo;
    }

    /**
     * 사장님 비밀번호 변경
     *
     * @param command
     * @return
     */
    public OwnerInfo updateOwnerPassword(OwnerCommand command, String afterPassword, HttpSession session){
        var OwnerInfo = ownerService.updateOwnerPassword(command, afterPassword); // 비밀번호 변경
        SessionUtil.removeLogoutOwner(session);                                   // 비밀번호 변경 후 재접속을 위해 session 값 삭제
        return OwnerInfo;
    }

    /**
     * 회원 탈퇴 (사장님 [DISABLE] 상태 변경)
     *
     * @param ownerToken
     * @param session
     */
    public void disableOwner(String ownerToken, HttpSession session){
        ownerService.disableOwner(ownerToken); // 사장 상태 [DISABLE] 변경
        SessionUtil.removeLogoutOwner(session);  // 회원 탈퇴 시 Session 정보 삭제
    }

    /**
     * 본인인증
     *
     * @param command
     * @return
     */
    public OwnerInfo authCheck(OwnerCommand command){
        var ownerInfo = ownerService.authCheck(command);
        return ownerInfo;
    }

    /**
     * 휴대폰번호로 비밀번호 변경 링크 발송
     *
     * @param command
     */
    public void passwordLindSendToSms(OwnerCommand command){
        naverSensService.sendNaverSens(NaverSensRequest.toPwdLinkInfo(command.getOwnerTel(), command.getOwnerToken()));
    }

    /**
     * 이메일로 비밀번호 변경 링크 발송
     *
     * @param command
     */
    public void passwordLindSendToMail(OwnerCommand command){
        MailSendRequest mailAuthNumberRequest = new MailPasswordLinkRequest(command.getOwnerMail() ,command.getOwnerToken());
        mailService.sendMail(mailAuthNumberRequest);
    }
}
