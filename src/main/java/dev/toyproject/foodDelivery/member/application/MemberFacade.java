package dev.toyproject.foodDelivery.member.application;

import dev.toyproject.foodDelivery.common.util.SessionUtil;
import dev.toyproject.foodDelivery.member.domain.MemberCommand;
import dev.toyproject.foodDelivery.member.domain.MemberInfo;
import dev.toyproject.foodDelivery.member.domain.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberService memberService;

    /**
     * 회원가입 진행
     *
     * @param command
     * @return
     */
    public MemberInfo registerMember(MemberCommand command){
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
    public MemberInfo loginMember(MemberCommand command, HttpSession session){
        var loginMemberInfo = memberService.loginMemberInfo(command.getMemberMail(), command.getMemberPwd()); // 로그인 정보 확인
        SessionUtil.setLoginMemberId(session, loginMemberInfo.getMemberToken()); // session 에 사용자 Token 정보 저장
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
}
