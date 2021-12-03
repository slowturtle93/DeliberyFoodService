package dev.toyproject.foodDelivery.owner.infrastructure;

import dev.toyproject.foodDelivery.common.exception.DuplicateKeyException;
import dev.toyproject.foodDelivery.common.exception.EntityNotFoundException;
import dev.toyproject.foodDelivery.owner.domain.Owner;
import dev.toyproject.foodDelivery.owner.domain.OwnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerReadImpl implements OwnerReader {

    private final OwnerRepository ownerRepository;

    /**
     * 사장님 정보 조회 By OwnerToken
     *
     * @param ownerToken
     * @return
     */
    @Override
    public Owner getOwnerByToken(String ownerToken) {
        return ownerRepository.findByOwnerTokenAndStatus(ownerToken, Owner.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 회원가입 진행 시 request 의 LoginId 의 중복여부 확인
     *
     * @param ownerLoginId
     */
    @Override
    public void duplicateCheckOwnerLoginId(String ownerLoginId) {
        Optional<Owner> member = ownerRepository.findByOwnerLoginId(ownerLoginId);
        if(!member.isEmpty()){ throw new DuplicateKeyException(); }
    }

    /**
     * 사용자 로그인 진행 By Mail, Password
     *
     * @param ownerLoginId
     * @param ownerPwd
     * @return
     */
    @Override
    public Owner getLoginOwner(String ownerLoginId, String ownerPwd) {
        return ownerRepository.findByOwnerLoginIdAndOwnerPwdAndStatus(ownerLoginId, ownerPwd, Owner.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 사장 정보 조회 By Token, Password
     *
     * @param ownerToken
     * @param ownerPwd
     * @return
     */
    @Override
    public Owner getOwnerByTokenAndPwd(String ownerToken, String ownerPwd) {
        return ownerRepository.findByOwnerTokenAndOwnerPwdAndStatus(ownerToken, ownerPwd, Owner.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 로그인 아이디, 휴대폰 번호로 본인인증
     *
     * @param ownerLoginId
     * @param ownerTel
     * @return
     */
    @Override
    public Owner authCheck(String ownerLoginId, String ownerTel) {
        return ownerRepository.findByOwnerLoginIdAndOwnerTelAndStatus(ownerLoginId, ownerTel, Owner.Status.ENABLE)
                .orElseThrow(EntityNotFoundException::new);
    }
}
