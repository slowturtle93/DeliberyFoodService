package dev.toyproject.foodDelivery.member.domain.address;

import java.util.List;

public interface MemberAddressReader {
    List<MemberAddress> getMemberAddressByMemberTokenAndStatus(String memberToken, MemberAddress.Status status);
}
