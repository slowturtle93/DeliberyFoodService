package dev.toyproject.foodDelivery.owner.infrastructure;

import dev.toyproject.foodDelivery.common.exception.DuplicateKeyException;
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
}
