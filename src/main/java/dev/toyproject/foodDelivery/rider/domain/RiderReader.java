package dev.toyproject.foodDelivery.rider.domain;

public interface RiderReader {

    void DuplicateLoginId(String riderLoginId);

    Rider getLoginRider(String riderLoginId, String riderPwd);

    Rider getRiderByToken(String riderToken);

    Rider getRiderByTokenAndPwd(String riderToken, String riderPwd);

    Rider authCheck(String riderLoginId, String riderTel);
}
