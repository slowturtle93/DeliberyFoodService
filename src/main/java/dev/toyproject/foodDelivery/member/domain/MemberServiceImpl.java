package dev.toyproject.foodDelivery.member.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * MEMBER 도메인 요구사항 구현체
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberStore memberStore;
    private final MemberReader memberReader;

    /**
     * 사용자 회원가입
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public MemberInfo registerMember(MemberCommand command) {
        var initMember = command.toEntity();  // command convert to initMember
        Member member = memberStore.store(initMember); // initMember register
        return new MemberInfo(member);
    }

    /**
     * 사용자 로그인 진행
     *
     * @param memberMail
     * @param memberPwd
     * @return
     */
    @Override
    public MemberInfo loginMemberInfo(String memberMail, String memberPwd) {
        Member member = memberReader.getLoginMember(memberMail, memberPwd); // Mail, Pwd 조건으로 사용자 정보 조회
        return new MemberInfo(member);
    }
}
