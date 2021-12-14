package dev.toyproject.foodDelivery.member.domain.address;

import dev.toyproject.foodDelivery.member.domain.MemberInfo;

import java.util.List;

public interface MemberAddressReader {

    List<MemberAddress> getMemberAddressByMemberTokenAndStatus(String memberToken, MemberAddress.Status status);

    MemberAddress getMemberAddressByMemberAddressToken(String memberAddressToken);

    List<MemberAddress> getMemberAddressByMemberToken(String memberToken);

    List<MemberInfo.Address> getMemberAddressSeries(List<MemberAddress> memberAddressList);
}
