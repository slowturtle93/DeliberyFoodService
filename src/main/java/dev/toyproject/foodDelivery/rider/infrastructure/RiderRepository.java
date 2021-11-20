package dev.toyproject.foodDelivery.rider.infrastructure;

import dev.toyproject.foodDelivery.rider.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, String> {

}
