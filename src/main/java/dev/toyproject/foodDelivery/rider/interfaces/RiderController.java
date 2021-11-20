package dev.toyproject.foodDelivery.rider.interfaces;

import dev.toyproject.foodDelivery.common.response.CommonResponse;
import dev.toyproject.foodDelivery.rider.application.RiderFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
