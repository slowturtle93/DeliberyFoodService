package dev.toyproject.foodDelivery.mapper.coupon;

import dev.toyproject.foodDelivery.coupon.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {
    public List<Coupon> findCouponEnable();
}
