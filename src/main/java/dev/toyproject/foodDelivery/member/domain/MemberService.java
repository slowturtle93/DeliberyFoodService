package dev.toyproject.foodDelivery.member.domain;

/**
 * MEMBER 도메인 요구사항
 */
public interface MemberService {

    MemberInfo registerMember(MemberCommand command);

    MemberInfo loginMemberInfo(String memberMail, String memberPwd);
}
