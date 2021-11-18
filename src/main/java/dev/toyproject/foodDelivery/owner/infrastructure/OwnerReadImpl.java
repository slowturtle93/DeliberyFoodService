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
}
