package dev.toyproject.foodDelivery.address.domain;

import dev.toyproject.foodDelivery.address.infrastructure.KakaoAddressResponse;
import dev.toyproject.foodDelivery.address.infrastructure.RetrofitKakaoAddressApi;
import dev.toyproject.foodDelivery.common.util.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final RetrofitUtils retrofitUtils;
    private final RetrofitKakaoAddressApi retrofitKakaoAddressApi;

    /**
     * 특정 주소 검색
     *
     * @param query
     * @return
     */
    @Override
    public AddressFragment searchAddress(String query) {
        var call = retrofitKakaoAddressApi.KakaoAddressRequest(query);

        KakaoAddressResponse.response response = retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);

        if(response.getDocuments().size() > 0){
            AddressInfo.RoadAddress roadAddress = response.getDocuments().get(0).getRoadAddress();
            return new AddressFragment(roadAddress);
        }else{
            return new AddressFragment();
        }
    }
}
