package dev.toyproject.foodDelivery.order.application;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.notification.fcm.domain.FcmNotificationRequest;
import dev.toyproject.foodDelivery.notification.fcm.domain.FcmService;
import dev.toyproject.foodDelivery.notification.fcm.infrastructrue.FcmNotificationInfo;
import dev.toyproject.foodDelivery.order.domain.OrderCommand;
import dev.toyproject.foodDelivery.order.domain.OrderInfo;
import dev.toyproject.foodDelivery.order.domain.OrderInfoMapper;
import dev.toyproject.foodDelivery.order.domain.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final RedisCacheUtil redisCacheUtil;
    private final FcmService fcmService;

    /**
     * 장바구니 메뉴 등록
     *
     * @param command
     */
    public List<OrderInfo.OrderBasketInfo> registerMenuBasket(OrderCommand.OrderBasketRequest command){
        return orderService.registerMenuBasket(command);
    }

    /**
     * 장바구니에서 특정 메뉴 삭제
     *
     * @param command
     */
    public List<OrderInfo.OrderBasketInfo> deleteMenuBasket(OrderCommand.OrderBasketRequest command){
        return orderService.deleteMenuBasket(command);
    }

    /**
     * 장바구니 메뉴 전체 삭제
     *
     * @param memberToken
     */
    public void deleteMenuBasketAll(String memberToken){
        orderService.deleteMenuBasketAll(memberToken);
    }

    /**
     * 장바구니 메뉴 조회
     *
     * @param memberToken
     */
    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken){
        return orderService.retrieveMenuBasket(memberToken);
    }

    /**
     * Redis 장바구니 메뉴 수량 변경
     *
     * @param command
     * @return
     */
    public List<OrderInfo.OrderBasketInfo> updateMenuBasketAmount(OrderCommand.OrderBasketRequest command){
        return orderService.updateMenuBasketAmount(command);
    }

    /**
     * 주문 정보 등록
     *
     * @param registerOrder
     * @return
     */
    public String registerOrder(OrderCommand.RegisterOrder registerOrder){
        var orderToken = orderService.registerOrder(registerOrder);            // 주문 정보 등록
        var deviceToken = redisCacheUtil.getDeviceTokenInfo(registerOrder.getMemberToken());
        fcmService.sendFcm(new FcmNotificationRequest(FcmNotificationInfo.FCM_ORDER_INIT_TITLE, FcmNotificationInfo.FCM_ORDER_INIT_MESSAGE, deviceToken));
        return orderToken;
    }

    /**
     * 주문 정보 조회
     *
     * @param orderToken
     * @return
     */
    public OrderInfo.OrderResponse retrieveOrder(String orderToken){
        return orderService.retrieveOrder(orderToken);
    }

    /**
     * 사용자의 주문 이력 list 조회
     *
     * @param memberToken
     * @return
     */
    public List<OrderInfo.OrderResponse> retrieveOrderList(String memberToken){
        return orderService.retrieveOrderList(memberToken);
    }

    /**
     * 주문 결제 요청
     *
     * @param paymentRequest
     */
    public OrderInfo.OrderPaymentRedirectUrl paymentOrder(OrderCommand.PaymentRequest paymentRequest){
        var response = orderService.paymentOrder(paymentRequest);
        return OrderInfo.OrderPaymentRedirectUrl.builder()
                .redirectUrl(response.getRedirectUrl())
                .build();
    }
}
