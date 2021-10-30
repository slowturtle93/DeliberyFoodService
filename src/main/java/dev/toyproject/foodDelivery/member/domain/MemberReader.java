package dev.toyproject.foodDelivery.member.domain;

public interface MemberReader {

    Member getLoginMember(String memberMail, String memberPwd);

    void DuplicateCheckMemberMail(String memberMail);

    Member getMemberByToken(String memberToken);

    Member getMemberByTokenAndPwd(String memberToken, String memberPwd);
}
