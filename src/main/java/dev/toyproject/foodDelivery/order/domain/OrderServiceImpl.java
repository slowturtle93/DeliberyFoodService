package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.order.domain.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final RedisCacheUtil redisCacheUtil;
    private final OrderFactory orderFactory;
    private final OrderStore orderStore;
    private final OrderMenuSeriesFactory orderMenuSeriesFactory;
    private final OrderRead orderRead;
    private final OrderInfoMapper orderInfoMapper;
    private final PaymentProcessor paymentProcessor;

    /**
     * 장바구니 메뉴 등록
     *
     * @param command
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> registerMenuBasket(OrderCommand.OrderBasketRequest command) {
        String hashKey = orderFactory.makeHashKey(command);
        orderFactory.orderBasketShopCheck(command);                       // 같은 가게 확인
        orderFactory.duplicationMenu(command,hashKey);                    // 메뉴 & 옵션 동일 여부 확인
        orderFactory.registerCacheMenuBasket(command, hashKey);           // Redis 등록
        return orderFactory.retrieveMenuBasket(command.getMemberToken()); // 등록된 장바구니 메뉴 조회
    }

    /**
     * 장바구니 특정 메뉴 삭제
     *
     * @param command
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> deleteMenuBasket(OrderCommand.OrderBasketRequest command) {
        String hashKey = orderFactory.makeHashKey(command);
        orderFactory.removeMenuBasket(command.getMemberToken(), hashKey);
        return orderFactory.retrieveMenuBasket(command.getMemberToken());
    }

    /**
     * Redis 장바구니 메뉴 전체 삭제
     *
     * @param memberToken
     */
    @Override
    public void deleteMenuBasketAll(String memberToken) {
        redisCacheUtil.removeMenuBasketAll(memberToken);
    }

    /**
     * Redis 장바구니 메뉴 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken) {
        return orderFactory.retrieveMenuBasket(memberToken);
    }

    /**
     * Redis 장바구니 메뉴 수량 변경
     *
     * @param command
     * @return
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> updateMenuBasketAmount(OrderCommand.OrderBasketRequest command) {
        String hashKey = orderFactory.makeHashKey(command);
        return orderFactory.updateMenuBasketAmount(command, hashKey);
    }

    /**
     * 주문 정보 등록
     *
     * @param registerOrder
     * @return
     */
    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder registerOrder) {
        Order order = orderStore.store(registerOrder.toEntity()); // 주문 정보 저장
        orderMenuSeriesFactory.store(order, registerOrder);       // 주문 정보 하위 객체 저장
        return order.getOrderToken();
    }

    /**
     * 주문 정보 조회
     *
     * @param orderToken
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public OrderInfo.OrderResponse retrieveOrder(String orderToken) {
        var order = orderRead.getOrder(orderToken);         // 주문 정보 조회
        return  orderInfoMapper.of(order);
    }

    /**
     * 사용자의 주문 이력 list 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderInfo.OrderResponse> retrieveOrderList(String memberToken) {
        var orderList = orderRead.getOrderList(memberToken);
        var response = orderInfoMapper.orderInfoList(orderList);
        return response;
    }

    /**
     * 주문 결제 요청
     *
     * @param paymentRequest
     */
    @Override
    @Transactional
    public OrderInfo.OrderPaymentRedirectUrl paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        var orderToken = paymentRequest.getOrderToken(); // 주문 Token 정보 get
        var order = orderRead.getOrder(orderToken);      // 주문 정보 조회
        order.orderComplete();                                  // 주문 완료
        return paymentProcessor.pay(order, paymentRequest); // 주문 결제 요청
    }
}
