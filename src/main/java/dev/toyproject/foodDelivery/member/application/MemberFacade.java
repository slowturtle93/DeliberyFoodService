package dev.toyproject.foodDelivery.member.application;

import dev.toyproject.foodDelivery.common.util.RandomAccessGenerator;
import dev.toyproject.foodDelivery.common.util.redis.SessionUtil;
import dev.toyproject.foodDelivery.member.domain.MemberCommand;
import dev.toyproject.foodDelivery.member.domain.MemberInfo;
import dev.toyproject.foodDelivery.member.domain.MemberService;
import dev.toyproject.foodDelivery.notification.email.domain.MailService;
import dev.toyproject.foodDelivery.notification.email.infrastructure.MailAuthNumberRequest;
import dev.toyproject.foodDelivery.notification.sms.domain.NaverSensService;
import dev.toyproject.foodDelivery.notification.sms.infrastructure.NaverSensRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberService memberService;
    private final NaverSensService naverSensService;
    private final MailService mailService;

    /**
     * 회원가입 진행
     *
     * @param command
     * @return
     */
    public MemberInfo.Main registerMember(MemberCommand.Main command){
        var memberInfo = memberService.registerMember(command); // 사용자 등록
        return memberInfo;
    }

    /**
     * 사용자 로그인 진행
     *
     * @param command
     * @param session
     * @return
     */
    public MemberInfo.Main loginMember(MemberCommand.Main command, HttpSession session){
        var loginMemberInfo = memberService.loginMemberInfo(command.getMemberMail(), command.getMemberPwd()); // 로그인 정보 확인
        SessionUtil.setLoginMemberToken(session, loginMemberInfo.getMemberToken()); // session 에 사용자 Token 정보 저장
        return loginMemberInfo;
    }

    /**
     * 사용자 로그아웃 진행
     *
     * @param session
     */
    public void logoutMember(HttpSession session){
        SessionUtil.removeLogoutMember(session); // session 에 사용자 Token 정보 삭제
    }

    /**
     * 사용자 이메일 중복 확인
     *
     * @param memberMail
     */
    public void duplicateMemberMail(String memberMail){
        memberService.duplicateMemberMail(memberMail); // 이메일 중복 확인
    }

    /**
     * 사용자 정보 수정
     *
     * @param command
     * @return
     */
    public MemberInfo.Main updateMember(MemberCommand.Main command){
        var memberInfo = memberService.updateMember(command); // 사용자 정보 수정
        return memberInfo;
    }

    /**
     * 사용자 비밀번호 변경
     *
     * @param command
     * @return
     */
    public MemberInfo.Main updateMemberPassword(MemberCommand.Main command, String afterPassword, HttpSession session){
        var MemberInfo = memberService.updateMemberPassword(command, afterPassword); // 비밀번호 변경
        SessionUtil.removeLogoutMember(session); // 비밀번호 변경 후 재접속을 위해 session 값 삭제
        return MemberInfo;
    }

    /**
     * 회원 탈퇴 (사용자 DISABLE 상태 변경)
     *
     * @param memberToken
     * @param session
     */
    public void disableMember(String memberToken, HttpSession session){
        memberService.disableMember(memberToken); // 사용자 상태 [DISABLE] 변경
        SessionUtil.removeLogoutMember(session);  // 회원 탈퇴 시 Session 정보 삭제
    }

    /**
     * 본인인증 확인
     */
    public MemberInfo.Main authCheck(MemberCommand.Main command){
        var memberInfo = memberService.authCheck(command);
        return memberInfo;
    }

    /**
     * 휴대폰 번호로 인증번호 발송
     *
     * @param command
     */
    public void authNumberSms(MemberCommand.Main command){
        String randomNumber = RandomAccessGenerator.authNumberGenerator();
        memberService.authNumberRegister(command, randomNumber);
        naverSensService.sendNaverSens(NaverSensRequest.toAuthNumberInfo(command.getMemberTel(), randomNumber));
    }

    /**
     * 이메일로 인증번호 발송
     *
     * @param command
     */
    public void authNumberMail(MemberCommand.Main command){
        String randomNumber = RandomAccessGenerator.authNumberGenerator();
        memberService.authNumberRegister(command, randomNumber);
        mailService.sendMail(new MailAuthNumberRequest(command.getMemberMail(), randomNumber));
    }

    /**
     * 인증번호 확인
     *
     * @param memberToken
     * @param authNumber
     */
    public void authNumberCheck(String memberToken, String authNumber){
        memberService.authNumberCheck(memberToken, authNumber);
    }

    /**
     * 신규 비밀번호 업데이트
     *
     * @param command
     */
    public void newPasswordUpdate(MemberCommand.Main command){
        memberService.newPasswordUpdate(command);
    }
}
