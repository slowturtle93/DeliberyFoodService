package dev.toyproject.foodDelivery.member.infrastructure.address;

import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddressFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAddressFactoryImpl implements MemberAddressFactory {

    /**
     * 활성화 된 배달 주소 Disable 상태 변경
     *
     * @param memberAddressList
     */
    @Override
    public void memberAddressListDisable(List<MemberAddress> memberAddressList) {
        var addressList = memberAddressList;

        addressList.forEach(memberAddress ->{
            var memberAddressInfo = memberAddress;
            memberAddressInfo.disable();
        });
    }
}
