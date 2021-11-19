package dev.toyproject.foodDelivery.owner.interfaces;

import dev.toyproject.foodDelivery.common.aop.LoginCheck;
import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.owner.application.OwnerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/owner")
public class OwnerController {

    private final OwnerFacade ownerFacade;

    /**
     * 이메일, 비밀번호 request 를 받아 회원가입 진행
     *
     * @param request
     * @return
     */
    @PostMapping
    public CommonResponse registerOwner(@Valid OwnerDto.RegisterRequest request){
        var Command = request.toCommand();              // request Data Convert (Command)
        var ownerInfo = ownerFacade.registerOwner(Command);  // Owner 정보 조회
        var response = new OwnerDto.response(ownerInfo);                // OwnerInfo Data Convert (response)
        return CommonResponse.success(response);
    }

    /**
     * 사장님 로그인 아이디 중복확인
     *
     * @param ownerLoginId
     * @return
     */
    @GetMapping("/duplicatedOwnerLoginId/{ownerLoginId}")
    public CommonResponse duplicateOwnerLoginId(@PathVariable("ownerLoginId") @NotNull String ownerLoginId){
        ownerFacade.duplicateOwnerLoginId(ownerLoginId); // 이메일 중복 확인
        return CommonResponse.success("OK");
    }

    /**
     * 사장님 로그인 진행
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public CommonResponse loginOwner(@Valid OwnerDto.LoginRequest request, HttpSession session){
        var Command = request.toCommand();                       // request Data Convert (Command)
        var ownerInfo = ownerFacade.loginOwner(Command, session);     // OWNER 로그인
        var response = new OwnerDto.response(ownerInfo);                         // OwnerInfo Data Convert (response)
        return CommonResponse.success(response);
    }

    /**
     * 사장님 로그아웃 진행
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    @LoginCheck(type = LoginCheck.UserType.OWNER)
    public CommonResponse logoutOwner(HttpSession session){
        ownerFacade.logoutOwner(session); // OWNER 로그아웃
        return CommonResponse.success("OK");
    }
}
