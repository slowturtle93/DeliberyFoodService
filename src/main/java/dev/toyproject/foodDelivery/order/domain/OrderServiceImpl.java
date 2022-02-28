package dev.toyproject.foodDelivery.order.domain;

import dev.toyproject.foodDelivery.order.domain.kafka.KafkaOrderPaymentProducer;
import dev.toyproject.foodDelivery.order.domain.payment.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderFactory orderFactory;
    private final OrderStore orderStore;
    private final OrderRead orderRead;
    private final OrderInfoMapper orderInfoMapper;
    private final PaymentProcessor paymentProcessor;
    private final PaymentStore paymentStore;
    private final PaymentRead paymentRead;
    private final KafkaOrderPaymentProducer kafkaOrderPaymentProducer;

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
        orderFactory.removeMenuBasketAll(memberToken);
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
        orderFactory.orderPriceValidator(registerOrder);          // 가격 정보 확인
        Order order = orderStore.store(registerOrder.toEntity()); // 주문 정보 저장
        orderFactory.store(order, registerOrder);                 // 주문 정보 하위 객체 저장
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
    public OrderInfo.OrderAPIPaymentResponse paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        var orderToken = paymentRequest.getOrderToken();                                         // 주문 Token 정보 get
        var order = orderRead.getOrder(orderToken);                                              // 주문 정보 조회
        var APIResponse = paymentProcessor.pay(order, paymentRequest);      // 주문 결제 요청
        var paymentCommand = orderInfoMapper.of(APIResponse);                // 결제 Entity Convert
        var payment= paymentStore.store(paymentCommand.toEntity());                            // 결제 정보 등록
        orderFactory.setRedisCacheOrderPaymentToken(order.getOrderToken(), payment.getPaymentToken());  // 결제 토큰 Redis 저장
        order.orderComplete();                                                                          // 주문 완료 상태 변경
        return APIResponse;
    }

    /**
     * 카카오페이 결제 승인 API 요청
     *
     * @param pgToken
     * @param paymentToken
     */
    @Override
    @Transactional
    public void orderPaymentKakaoSuccess(String pgToken, String paymentToken) {
        var payment = paymentRead.getPayment(paymentToken);
        var paymentRequest = orderFactory.approveRequestConvertPayment(payment, pgToken);
        paymentProcessor.approvePay(paymentRequest);
        payment.paymentComplete();
        orderFactory.orderCouponIssueStatusUsed(payment);
        kafkaOrderPaymentProducer.registerKafkaMessage(orderFactory.orderPaymentConfirmInfo(payment.getPaymentToken()));
    }

    /**
     * Toss Pay 결제완료 처리
     *
     * @param paymentToken
     */
    @Override
    @Transactional
    public void orderPaymentTossSuccess(String paymentToken) {
        var payment = paymentRead.getPayment(paymentToken);
        payment.paymentComplete();
        orderFactory.orderCouponIssueStatusUsed(payment);
        kafkaOrderPaymentProducer.registerKafkaMessage(orderFactory.orderPaymentConfirmInfo(payment.getPaymentToken()));
    }

    /**
     * 주문 정보 [주문 승인] 상태 변경
     *
     * @param command
     */
    @Override
    @Transactional
    public void orderApproval(OrderCommand.OrderPaymentStatusRequest command) {
        var order = orderRead.getOrder(command.getOrderToken());
        order.orderApproval();
    }

    /**
     * 주문 정보 [주문 취소] 상태 변경
     *
     * @param command
     */
    @Override
    @Transactional
    public void orderCancel(OrderCommand.OrderPaymentStatusRequest command) {
        var payment = paymentRead.getPaymentByOrderToken(command.getOrderToken());
        var order = orderRead.getOrder(command.getOrderToken());
        payment.paymentRefund();
        order.orderCancel();
        if(!StringUtils.isEmpty(order.getCouponIssueToken())) { orderFactory.orderCouponIssueStatusInit(order); } // 주문 취소 후 발행한 쿠폰 상태 [INIT] 변경
        paymentProcessor.cancelPay(new OrderCommand.PaymentCancelRequest(payment.getPaymentToken(), PayMethod.valueOf(payment.getPaymentType())));
    }

    /**
     * 주문 정보 [배송 준비] 상태 변경
     *
     * @param command
     */
    @Override
    @Transactional
    public void OrderDeliveryPrepare(OrderCommand.OrderPaymentStatusRequest command) {
        var order = orderRead.getOrder(command.getOrderToken());
        order.deliveryPrepare();
    }

    /**
     * 주문 정보 [배송 중] 상태 변경
     *
     * @param command
     */
    @Override
    @Transactional
    public void OrderInDelivery(OrderCommand.OrderPaymentStatusRequest command) {
        var order = orderRead.getOrder(command.getOrderToken());
        order.inDelivery();
    }
}
