package dev.toyproject.foodDelivery.owner.infrastructure;

import dev.toyproject.foodDelivery.owner.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String> {

    Optional<Owner> findByOwnerLoginId(String ownerLoginId);

}
