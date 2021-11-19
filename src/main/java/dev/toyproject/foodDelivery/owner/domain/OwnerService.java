package dev.toyproject.foodDelivery.owner.domain;

public interface OwnerService {

    OwnerInfo registerOwner(OwnerCommand command);

    void duplicateOwnerLoginId(String ownerLoginId);

    OwnerInfo loginOwnerInfo(String ownerLoginId, String ownerPwd);

    OwnerInfo updateOwner(OwnerCommand command);
}
