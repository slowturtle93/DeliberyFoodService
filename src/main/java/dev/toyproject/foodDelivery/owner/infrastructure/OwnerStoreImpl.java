package dev.toyproject.foodDelivery.owner.infrastructure;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.owner.domain.Owner;
import dev.toyproject.foodDelivery.owner.domain.OwnerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerStoreImpl implements OwnerStore {

    private final OwnerRepository ownerRepository;

    /**
     * 사장 회원가입
     *
     * @param owner
     * @return
     */
    @Override
    public Owner store(Owner owner) {

        if(StringUtils.isEmpty(owner.getOwnerLoginId()))      throw new InvalidParamException("owner.getOwnerLoginId()");
        if(StringUtils.isEmpty(owner.getOwnerMail()))         throw new InvalidParamException("owner.getOwnerMail()");
        if(StringUtils.isEmpty(owner.getOwnerToken()))        throw new InvalidParamException("owner.getOwnerToken()");
        if(StringUtils.isEmpty(owner.getOwnerPwd()))          throw new InvalidParamException("owner.getOwnerPwd()");
        if(StringUtils.isEmpty(owner.getOwnerTel()))          throw new InvalidParamException("owner.getOwnerTel()");
        if(StringUtils.isEmpty(owner.getOwnerBirth()))        throw new InvalidParamException("owner.getOwnerBirth()");
        if(StringUtils.isEmpty(owner.getOwnerName()))         throw new InvalidParamException("owner.getOwnerName()");
        if(owner.getStatus() == null)                         throw new InvalidParamException("owner.getStatus()");

        return ownerRepository.save(owner);
    }
}
