package dev.toyproject.foodDelivery.rider.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.rider.application.RiderFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rider")
public class RiderController {

    private final RiderFacade riderFacade;

    /**
     * 라이더 등록
     *
     * @param request
     * @return
     */
    @PostMapping
    public CommonResponse registerRider(@Valid RiderDto.RegisterRequest request){
        var Command = request.toCommand();              // request Data Convert (Command)
        var riderInfo = riderFacade.registerRider(Command); // Rider 정보 조회
        var response = new RiderDto.response(riderInfo);             // RiderInfo Data Convert (response)
        return CommonResponse.success(response);
    }

    /**
     * 아이디 중복 확인
     *
     * @param loginId
     * @return
     */
    @GetMapping("/duplicate/{loginId}")
    public CommonResponse duplicateLoginId(@PathVariable("loginId") @NotNull String loginId){
        riderFacade.duplicateLoginId(loginId); // 아이디 중복 확인
        return CommonResponse.success("OK");
    }

    /**
     * 라이더 로그인 진행
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public CommonResponse loginMember(@Valid RiderDto.LoginRequest request, HttpSession session){
        var Command = request.toCommand();                    // request Data Convert (Command)
        var riderInfo = riderFacade.loginRider(Command, session); // Rider 로그인
        var response = new RiderDto.response(riderInfo);                   // RiderInfo Data Convert (response)
        return CommonResponse.success(response);
    }
}
