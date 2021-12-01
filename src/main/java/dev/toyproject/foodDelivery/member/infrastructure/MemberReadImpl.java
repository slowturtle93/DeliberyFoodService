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

    /**
     * 사용자 정보 조회 By MemberToken
     *
     * @param memberToken
     * @return
     */
    @Override
    public Member getMemberByToken(String memberToken) {
        return memberRepository.findByMemberTokenAndStatus(memberToken, Member.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 사용자 정보 조회 By Token, Password
     *
     * @param memberToken
     * @param memberPwd
     * @return
     */
    @Override
    public Member getMemberByTokenAndPwd(String memberToken, String memberPwd) {
        return memberRepository.findByMemberTokenAndMemberPwdAndStatus(memberToken, memberPwd, Member.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 본인인증
     *
     * @param memberMail
     * @param memberTel
     * @return
     */
    @Override
    public Member authCheck(String memberMail, String memberTel) {
        return memberRepository.findByMemberMailAndMemberTelAndStatus(memberMail, memberTel, Member.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }
}
