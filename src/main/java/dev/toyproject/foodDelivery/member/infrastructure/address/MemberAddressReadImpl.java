package dev.toyproject.foodDelivery.member.infrastructure.address;

import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.member.domain.MemberInfo;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddressReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAddressReadImpl implements MemberAddressReader {

    private final MemberAddressRepository memberAddressRepository;

    /**
     * 활성화 된 배달 주소 LIST 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<MemberAddress> getMemberAddressByMemberTokenAndStatus(String memberToken, MemberAddress.Status status) {
        return memberAddressRepository.findByMemberTokenAndStatus(memberToken, status);
    }

    /**
     * 특정 주소 검색
     *
     * @param memberAddressToken
     * @return
     */
    @Override
    public MemberAddress getMemberAddressByMemberAddressToken(String memberAddressToken) {
        return memberAddressRepository.findByMemberAddressToken(memberAddressToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 상태 [DELETE] 제외 한 배달 주소 LIST 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<MemberAddress> getMemberAddressByMemberToken(String memberToken) {
        return memberAddressRepository.findByMemberTokenAndStatusNot(memberToken, MemberAddress.Status.DELETE);
    }

    /**
     * memberAddress Entity Convert To MemberInfo.Address
     *
     * @param memberAddressList
     * @return
     */
    @Override
    public List<MemberInfo.Address> getMemberAddressSeries(List<MemberAddress> memberAddressList) {
        var addressList = memberAddressList;

        return addressList.stream()
                .map(memberAddressInfo ->{
                    return new MemberInfo.Address(memberAddressInfo);
                }).collect(Collectors.toList());
    }
}
