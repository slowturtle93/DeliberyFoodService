package dev.toyproject.foodDelivery.owner.domain;

public interface OwnerReader {

    void duplicateCheckOwnerLoginId(String ownerLoginId);

    Owner getLoginOwner(String ownerLoginId, String ownerPwd);
}
