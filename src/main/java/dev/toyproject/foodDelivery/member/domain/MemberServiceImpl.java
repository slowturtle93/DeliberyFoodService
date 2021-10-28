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
}
