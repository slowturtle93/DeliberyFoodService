package dev.toyproject.foodDelivery.owner.domain;

public interface OwnerService {

    OwnerInfo registerOwner(OwnerCommand command);

    void duplicateOwnerLoginId(String ownerLoginId);
}
