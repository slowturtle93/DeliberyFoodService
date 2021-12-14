package dev.toyproject.foodDelivery.member.infrastructure.address;

import dev.toyproject.foodDelivery.member.domain.address.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {
    List<MemberAddress> findByMemberTokenAndStatus(String memberToken, MemberAddress.Status status);

    Optional<MemberAddress> findByMemberAddressToken(String memberAddressToken);
}
