package dev.toyproject.foodDelivery.member.infrastructure.address;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddressStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAddressStoreImpl implements MemberAddressStore {

    private final MemberAddressRepository memberAddressRepository;

    /**
     * 사용자 배달 주소 저장
     *
     * @param memberAddress
     * @return
     */
    @Override
    public MemberAddress store(MemberAddress memberAddress) {

        if(StringUtils.isEmpty(memberAddress.getMemberToken())) throw new InvalidParamException("memberAddress.memberToken");
        if(memberAddress.getStatus() == null)                   throw new InvalidParamException("memberAddress.status");
        if(memberAddress.getAddressFragment() == null)          throw new InvalidParamException("memberAddress.addressFragment");

        return memberAddressRepository.save(memberAddress);
    }
}
