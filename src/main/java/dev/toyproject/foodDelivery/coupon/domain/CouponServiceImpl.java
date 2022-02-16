package dev.toyproject.foodDelivery.coupon.domain;

import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueCommand;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueInfo;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueRead;
import dev.toyproject.foodDelivery.coupon.domain.issue.CouponIssueStore;
import dev.toyproject.foodDelivery.mapper.coupon.CouponMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponStore couponStore;
    private final CouponRead couponRead;
    private final CouponFactory couponFactory;
    private final CouponIssueStore couponIssueStore;
    private final CouponIssueRead couponIssueRead;
    private final CouponMapper couponMapper;

    /**
     * 쿠폰 등록
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public CouponInfo.Main registerCoupon(CouponCommand.Register command) {
        var coupon = couponStore.store(command.toEntity());
        return new CouponInfo.Main(coupon);
    }

    /**
     * 쿠폰 정보 수정
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public CouponInfo.Main updateCoupon(CouponCommand.CouponUpdate command) {
        var coupon = couponRead.getCoupon(command.getCouponToken());
        coupon.update(command);
        return new CouponInfo.Main(coupon);
    }

    /**
     * 쿠폰 상태 [ENABLE] 변경
     *
     * @param couponToken
     * @return
     */
    @Override
    @Transactional
    public CouponInfo.Main enable(String couponToken) {
        var coupon = couponRead.getCoupon(couponToken);
        coupon.enable();
        return new CouponInfo.Main(coupon);
    }

    /**
     * 등록 된 쿠폰 list 조회
     *
     * @param shopToken
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CouponInfo.Main> retrieveCouponList(String shopToken) {
        var couponList = couponRead.getCouponList(shopToken);
        return couponFactory.convertCouponInfoMain(couponList);
    }

    /**
     * 발행 가능한 쿠폰 조회
     *
     * @param shopToken
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CouponInfo.Main> retrieveCouponEnable(String shopToken) {
        var couponList = couponMapper.findCouponEnable();
        return couponFactory.convertCouponInfoMain(couponList);
    }

    /**
     * 사용자 쿠폰 발행
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public CouponIssueInfo.Main registerCouponIssue(CouponIssueCommand.Main command) {
        var duplicationCoupon = couponIssueRead.duplicationCouponIssue(command);
        if (!duplicationCoupon){
            var couponIssue = couponIssueStore.save(command.toEntity());
            return new CouponIssueInfo.Main(couponIssue);
        }else {
            throw new DuplicateKeyException("이미 발행된 쿠폰입니다.");
        }
    }

    /**
     * 발행한 쿠폰  list 조회
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public List<CouponIssueInfo.Main> retrieveCouponIssue(CouponIssueCommand.RetrieveCouponIssue command) {
        couponMapper.updateCouponIssueStatusEnd(command);
        var couponIssueInfoList = couponMapper.findAllCouponIssueEnable(command);
        return couponFactory.convertCouponIssueInfoMain(couponIssueInfoList);
    }

    /**
     * 발행 쿠폰 조회
     *
     * @param couponIssueToken
     * @return
     */
    @Override
    public CouponInfo.Main retrieveCouponIssuePrice(String couponIssueToken) {
        var coupon = couponMapper.findCouponInfo(couponIssueToken);
        return new CouponInfo.Main(coupon);
    }
}
