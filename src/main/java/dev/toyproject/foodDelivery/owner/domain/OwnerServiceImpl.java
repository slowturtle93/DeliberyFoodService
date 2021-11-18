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
    private final OwnerReader ownerReader;

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

    /**
     * 사장님 로그인 아이디 중복 확인
     *
     * @param ownerLoginId
     * @return
     */
    @Override
    public void duplicateOwnerLoginId(String ownerLoginId) {
        ownerReader.duplicateCheckOwnerLoginId(ownerLoginId); // loginId Duplicate Check;
    }

    /**
     * 사장님 로그인 진행
     * @param ownerLoginId
     * @param ownerPwd
     * @return
     */
    @Override
    public OwnerInfo loginOwnerInfo(String ownerLoginId, String ownerPwd) {
        Owner owner = ownerReader.getLoginOwner(ownerLoginId, ownerPwd); // loginId, Pwd 조건으로 사장 정보 조회
        return new OwnerInfo(owner);
    }
}
