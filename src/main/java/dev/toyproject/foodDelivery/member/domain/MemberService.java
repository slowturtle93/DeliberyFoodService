package dev.toyproject.foodDelivery.member.domain;

/**
 * MEMBER 도메인 요구사항
 */
public interface MemberService {

    MemberInfo.Main registerMember(MemberCommand.Main command);

    MemberInfo.Main loginMemberInfo(String memberMail, String memberPwd);

    void duplicateMemberMail(String memberMail);

    MemberInfo.Main updateMember(MemberCommand.Main command);

    MemberInfo.Main updateMemberPassword(MemberCommand.Main command, String afterPassword);

    MemberInfo.Main disableMember(String memberToken);

    MemberInfo.Main authCheck(MemberCommand.Main command);

    void authNumberRegister(MemberCommand.Main command, String authNumber);

    void authNumberCheck(String memberToken, String authNumber);

    void newPasswordUpdate(MemberCommand.Main command);

    MemberInfo.Address registerAddress(MemberCommand.Address command);

    MemberInfo.Address updateAddress(String memberAddressToken, MemberCommand.Address command);

    void deleteAddress(String memberAddressToken);
}
