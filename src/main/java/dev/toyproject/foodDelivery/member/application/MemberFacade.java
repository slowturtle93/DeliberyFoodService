package dev.toyproject.foodDelivery.member.application;

import dev.toyproject.foodDelivery.common.util.redis.SessionUtil;
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
    public MemberInfo updateMember(MemberCommand command){
        var memberInfo = memberService.updateMember(command); // 사용자 정보 수정
        return memberInfo;
    }

    /**
     * 사용자 비밀번호 변경
     *
     * @param command
     * @return
     */
    public MemberInfo updateMemberPassword(MemberCommand command, String afterPassword, HttpSession session){
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
}
