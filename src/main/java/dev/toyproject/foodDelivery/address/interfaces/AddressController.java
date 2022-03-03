package dev.toyproject.foodDelivery.address.interfaces;

import dev.toyproject.foodDelivery.address.application.AddressFacade;
import dev.toyproject.foodDelivery.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressFacade addressFacade;

    /**
     * 주소 검색
     *
     * @param query
     */
    @GetMapping("/{query}")
    public CommonResponse retrieveAddress(@PathVariable("query") String query) {
        var response = addressFacade.getAddress(query);
        return CommonResponse.success(response);
    }
}
