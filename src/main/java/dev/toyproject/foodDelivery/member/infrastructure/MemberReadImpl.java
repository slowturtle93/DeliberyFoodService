package dev.toyproject.foodDelivery.member.infrastructure;

import dev.toyproject.foodDelivery.common.exception.DuplicateKeyException;
import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.member.domain.Member;
import dev.toyproject.foodDelivery.member.domain.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    /**
     * 사용자 Mail 중복여부 확인
     *
     * @param memberMail
     */
    @Override
    public void DuplicateCheckMemberMail(String memberMail) {
        Optional<Member> member = memberRepository.findByMemberMail(memberMail);
        if(!member.isEmpty()){ throw new DuplicateKeyException(); }
    }
}
