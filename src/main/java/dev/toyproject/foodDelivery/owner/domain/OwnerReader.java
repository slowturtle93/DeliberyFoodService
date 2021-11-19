package dev.toyproject.foodDelivery.owner.domain;

public interface OwnerReader {

    Owner getOwnerByToken(String ownerToken);

    void duplicateCheckOwnerLoginId(String ownerLoginId);

    Owner getLoginOwner(String ownerLoginId, String ownerPwd);
}
