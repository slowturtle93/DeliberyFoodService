package dev.toyproject.foodDelivery.owner.infrastructure;

import dev.toyproject.foodDelivery.owner.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {

}
