package dev.toyproject.foodDelivery.address.infrastructure;

import dev.toyproject.foodDelivery.address.domain.AddressInfo;
import lombok.Getter;

import java.util.ArrayList;

public class KakaoAddressResponse {

    @Getter
    public static class response{
        ArrayList<AddressInfo.Documents> documents;
        AddressInfo.Meta meta;
    }
}
