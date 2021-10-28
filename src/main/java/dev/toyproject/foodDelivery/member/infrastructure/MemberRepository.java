package dev.toyproject.foodDelivery.member.infrastructure;

import dev.toyproject.foodDelivery.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
