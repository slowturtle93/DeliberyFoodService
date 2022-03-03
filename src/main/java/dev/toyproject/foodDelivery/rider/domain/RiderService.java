package dev.toyproject.foodDelivery.rider.domain;

import java.util.List;

public interface RiderService {

    RiderInfo registerRider(RiderCommand command);

    void duplicateLoginId(String loginId);

    RiderInfo loginRiderInfo(String riderLoginId, String riderPwd);

    RiderInfo updateRider(RiderCommand command);

    RiderInfo updateRiderPassword(RiderCommand command, String afterPassword);

    RiderInfo authCheck(RiderCommand command);

    void newPasswordUpdate(RiderCommand command);

    List<RiderInfo.AvailableOrders> retrieveEnableOrderList(RiderCommand.RiderCurrentLocation command);

    RiderInfo.AvailableOrders riderOrderPick(String orderToken);

    void riderOrderPickup(String orderToken);

    void riderOrderComplete(String orderToken);
}
