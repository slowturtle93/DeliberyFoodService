package dev.toyproject.foodDelivery.rider.domain;

public interface RiderService {

    RiderInfo registerRider(RiderCommand command);

    void duplicateLoginId(String loginId);

    RiderInfo loginRiderInfo(String riderLoginId, String riderPwd);

    RiderInfo updateRider(RiderCommand command);

    RiderInfo updateRiderPassword(RiderCommand command, String afterPassword);
}
