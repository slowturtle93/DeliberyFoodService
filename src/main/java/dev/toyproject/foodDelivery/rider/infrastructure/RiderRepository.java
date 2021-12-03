package dev.toyproject.foodDelivery.rider.infrastructure;

import dev.toyproject.foodDelivery.rider.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, String> {

    Optional<Rider> findByRiderLoginId(String loginId);

    Optional<Rider> findByRiderLoginIdAndRiderPwdAndStatus(String riderLoginId, String riderPWd, Rider.Status status);

    Optional<Rider> findByRiderTokenAndStatus(String riderToken, Rider.Status status);

    Optional<Rider> findByRiderTokenAndRiderPwdAndStatus(String riderToken, String riderPwd, Rider.Status status);

    Optional<Rider> findByRiderLoginIdAndRiderTelAndStatus(String riderLoginId, String riderTel, Rider.Status status);
}
