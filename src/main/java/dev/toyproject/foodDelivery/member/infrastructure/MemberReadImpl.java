package dev.toyproject.foodDelivery.member.infrastructure;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.member.domain.Member;
import dev.toyproject.foodDelivery.member.domain.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberReadImpl implements MemberReader {

    private final MemberRepository memberRepository;

    /**
     * 사용자 로그인 진행 By Mail, Password
     *
     * @param memberMail
     * @param memberPwd
     * @return
     */
    @Override
    public Member getLoginMember(String memberMail, String memberPwd) {
        return memberRepository.findByMemberMailAndMemberPwdAndStatus(memberMail, memberPwd, Member.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }
}
