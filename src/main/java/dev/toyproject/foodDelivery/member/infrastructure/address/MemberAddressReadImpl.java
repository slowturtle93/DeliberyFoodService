package dev.toyproject.foodDelivery.member.infrastructure.address;

import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import dev.toyproject.foodDelivery.member.domain.address.MemberAddressReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
