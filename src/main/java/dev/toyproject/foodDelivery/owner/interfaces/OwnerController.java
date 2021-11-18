package dev.toyproject.foodDelivery.owner.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.owner.application.OwnerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
