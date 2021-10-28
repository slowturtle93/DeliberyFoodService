package dev.toyproject.foodDelivery.member.application;

import dev.toyproject.foodDelivery.member.domain.MemberCommand;
import dev.toyproject.foodDelivery.member.domain.MemberInfo;
import dev.toyproject.foodDelivery.member.domain.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
