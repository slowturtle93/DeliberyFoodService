package dev.toyproject.foodDelivery.rider.domain;

public interface RiderReader {

    void DuplicateLoginId(String riderLoginId);

    Rider getLoginRider(String riderLoginId, String riderPwd);
}
