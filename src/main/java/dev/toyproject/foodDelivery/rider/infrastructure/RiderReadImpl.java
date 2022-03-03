package dev.toyproject.foodDelivery.rider.infrastructure;

import dev.toyproject.foodDelivery.common.exception.DuplicateKeyException;
import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.rider.domain.Rider;
import dev.toyproject.foodDelivery.rider.domain.RiderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RiderReadImpl implements RiderReader {

    private final RiderRepository riderRepository;

    /**
     * 로그인 아이디 중복 확인
     *
     * @param loginId
     */
    @Override
    public void DuplicateLoginId(String loginId) {
        Optional<Rider> riderInfo = riderRepository.findByRiderLoginId(loginId);
        if(!riderInfo.isEmpty()){ throw new DuplicateKeyException(); }
    }

    /**
     * 라이더 로그인 진행
     *
     * @param riderLoginId
     * @param riderPwd
     * @return
     */
    @Override
    public Rider getLoginRider(String riderLoginId, String riderPwd) {
        return riderRepository.findByRiderLoginIdAndRiderPwdAndStatus(riderLoginId, riderPwd, Rider.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 라이더 정보 조회
     *
     * @param riderToken
     * @return
     */
    @Override
    public Rider getRiderByToken(String riderToken) {
        return riderRepository.findByRiderTokenAndStatus(riderToken, Rider.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 라이더 정보 조회 By riderToken, riderPwd, status
     *
     * @param riderToken
     * @param riderPwd
     * @return
     */
    @Override
    public Rider getRiderByTokenAndPwd(String riderToken, String riderPwd) {
        return riderRepository.findByRiderTokenAndRiderPwdAndStatus(riderToken, riderPwd, Rider.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 라이더 본인인증
     *
     * @param riderLoginId
     * @param riderTel
     * @return
     */
    @Override
    public Rider authCheck(String riderLoginId, String riderTel) {
        return riderRepository.findByRiderLoginIdAndRiderTelAndStatus(riderLoginId, riderTel, Rider.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }
}
