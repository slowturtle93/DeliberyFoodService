package dev.toyproject.foodDelivery.mapper.coupon;

import dev.toyproject.foodDelivery.coupon.domain.Coupon;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssue;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueCommand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {
    public List<Coupon> findCouponEnable();

    public void updateCouponIssueStatusEnd(CouponIssueCommand.RetrieveCouponIssue command);

    public List<CouponIssue> findAllCouponIssueEnable(CouponIssueCommand.RetrieveCouponIssue command);
}
