package dev.toyproject.foodDelivery.member.interfaces;


import dev.toyproject.foodDelivery.common.aop.LoginCheck;
import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.member.application.MemberFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberFacade memberFacade;

    /**
     * 이메일, 비밀번호, 전화번호, 닉네임 request 를 받아 회원가입 진행
     *
     * @param request
     * @return
     */
    @PostMapping
    public CommonResponse registerMember(@Valid MemberDto.RegisterRequest request){
        var Command = request.toCommand();                 // request Data Convert (Command)
        var memberInfo = memberFacade.registerMember(Command); // Member 정보 조회
        var response = new MemberDto.response(memberInfo);                // MemberInfo Data Convert (response)
        return CommonResponse.success(response);
    }

    /**
     * 사용자 로그인 진행
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public CommonResponse loginMember(@Valid MemberDto.LoginRequest request, HttpSession session){
        var Command = request.toCommand();                       // request Data Convert (Command)
        var memberInfo = memberFacade.loginMember(Command, session); // MEMBER 로그인
        var response = new MemberDto.response(memberInfo);                      // MemberInfo Data Convert (response)
        return CommonResponse.success(response);
    }

    /**
     * 사용자 로그아웃 진행
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public CommonResponse logoutMember(HttpSession session){
        memberFacade.logoutMember(session); // MEMBER 로그아웃
        return CommonResponse.success("OK");
    }
}
