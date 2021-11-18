package dev.toyproject.foodDelivery.owner.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerStore ownerStore;

    /**
     * 사장 회원가입
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public OwnerInfo registerOwner(OwnerCommand command) {
        var initOwner = command.toEntity();  // command convert to initOwner
        Owner owner = ownerStore.store(initOwner);  // initOwner register
        return new OwnerInfo(owner);
    }
}
