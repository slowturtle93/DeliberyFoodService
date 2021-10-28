package dev.toyproject.foodDelivery.member.infrastructure;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.member.domain.Member;
import dev.toyproject.foodDelivery.member.domain.MemberStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * MEMBER 저장 관련 기능 구현체
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberStoreImpl implements MemberStore {

    private final MemberRepository memberRepository;

    /**
     * 사용자 회원가입
     *
     * @param member
     * @return
     */
    @Override
    public Member store(Member member) {

        if(StringUtils.isEmpty(member.getMemberMail()))     throw new InvalidParamException("member.getMemberMail()");
        if(StringUtils.isEmpty(member.getMemberToken()))    throw new InvalidParamException("member.getMemberToken()");
        if(StringUtils.isEmpty(member.getMemberPwd()))      throw new InvalidParamException("member.getMemberPwd()");
        if(StringUtils.isEmpty(member.getMemberTel()))      throw new InvalidParamException("member.getMemberTel()");
        if(StringUtils.isEmpty(member.getMemberNickname())) throw new InvalidParamException("member.getMemberNickname()");
        if(member.getStatus() == null)                      throw new InvalidParamException("member.getStatus()");

        return memberRepository.save(member);
    }
}
