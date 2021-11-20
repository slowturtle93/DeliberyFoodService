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

    /**
     * 사장님 정보 변경
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public OwnerInfo updateOwner(OwnerCommand command) {
        Owner owner = ownerReader.getOwnerByToken(command.getOwnerToken());                             // OWNER 정보 조회
        owner.updateOwnerInfo(command.getOwnerTel(), command.getOwnerBirth(), command.getOwnerMail());  // OWNER 정보 수정
        return new OwnerInfo(owner);
    }

    /**
     * 사장님 비밀번호 변경
     *
     * @param command
     * @return
     */
    @Override
    @Transactional
    public OwnerInfo updateOwnerPassword(OwnerCommand command, String afterPassword) {
        Owner owner = ownerReader.getOwnerByTokenAndPwd(command.getOwnerToken(), command.getOwnerPwd()); // Token, Pwd 조건으로 OWNER 정보 조회
        owner.updateOwnerPassword(afterPassword); // OWNER 비밀번호 변경
        return new OwnerInfo(owner);
    }
}
