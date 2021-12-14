package dev.toyproject.foodDelivery.member.infrastructure.address;

import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {
    List<MemberAddress> findByMemberTokenAndStatus(String memberToken, MemberAddress.Status status);
}
