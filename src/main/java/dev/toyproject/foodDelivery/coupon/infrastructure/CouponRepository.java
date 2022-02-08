package dev.toyproject.foodDelivery.coupon.infrastructure;

import dev.toyproject.foodDelivery.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
