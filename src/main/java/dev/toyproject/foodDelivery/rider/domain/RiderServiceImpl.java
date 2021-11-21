package dev.toyproject.foodDelivery.rider.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService{

    private final RiderStore riderStore;
    private final RiderReader riderReader;

    /**
     * 라이더 등록
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public RiderInfo registerRider(RiderCommand command) {
        var initRider = command.toEntity();
        Rider rider = riderStore.store(initRider);
        return new RiderInfo(rider);
    }

    /**
     * 로그인 아이디 중복 확인
     *
     * @param loginId
     */
    @Override
    public void duplicateLoginId(String loginId) {
        riderReader.DuplicateLoginId(loginId);
    }

    /**
     * 라이더 로그인 진행
     *
     * @param riderLoginId
     * @param riderPwd
     * @return
     */
    @Override
    public RiderInfo loginRiderInfo(String riderLoginId, String riderPwd) {
        Rider rider = riderReader.getLoginRider(riderLoginId, riderPwd); // Mail, Pwd 조건으로 사용자 정보 조회
        return new RiderInfo(rider);
    }

    /**
     * 라이더 정보 수정
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public RiderInfo updateRider(RiderCommand command) {
        Rider rider = riderReader.getRiderByToken(command.getRiderToken()); // MEMBER 정보 조회
        rider.updateRiderInfo(command); // MEMBER 정보 수정
        return new RiderInfo(rider);
    }

    /**
     * 라이더 비밀번호 변경
     *
     * @param command
     * @param afterPassword
     * @return
     */
    @Override
    @Transactional
    public RiderInfo updateRiderPassword(RiderCommand command, String afterPassword) {
        Rider rider = riderReader.getRiderByTokenAndPwd(command.getRiderToken(), command.getRiderPwd()); // Token, Pwd 조건으로 MEMBER 정보 조회
        rider.updateRiderPassword(afterPassword); // MEMBER 비밀번호 변경
        return new RiderInfo(rider);
    }
}
