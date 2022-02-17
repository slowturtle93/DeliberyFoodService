package dev.toyproject.foodDelivery.order.infrastructure;

import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.redis.RedisCacheUtil;
import dev.toyproject.foodDelivery.notification.common.domain.CommonApiService;
import dev.toyproject.foodDelivery.order.domain.*;
import dev.toyproject.foodDelivery.order.domain.menu.OrderMenu;
import dev.toyproject.foodDelivery.order.domain.payment.PayMethod;
import dev.toyproject.foodDelivery.order.domain.payment.Payment;
import dev.toyproject.foodDelivery.order.domain.payment.PaymentRead;
import dev.toyproject.foodDelivery.owner.domain.OwnerReader;
import dev.toyproject.foodDelivery.shop.domain.ShopReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFactoryImpl implements OrderFactory {

    private final RedisCacheUtil redisCacheUtil;
    private final OrderStore orderStore;
    private final PaymentRead paymentRead;
    private final OrderRead orderRead;
    private final ShopReader shopReader;
    private final OwnerReader ownerReader;
    private final CommonApiService commonApiService;

    /**
     * 장바구니 hashKey 생성
     * ( 메뉴 ID + 메뉴 옵션 ID )
     *
     * @param command
     * @return
     */
    @Override
    public String makeHashKey(OrderCommand.OrderBasketRequest command) {
        var menuBasket = command.getOrderBasketMenu();

        var menuBasketId = menuBasket.getId();
        StringBuilder menuOptionBasketId = new StringBuilder();

        menuBasket.getOrderBasketMenuOptionGroupList().forEach(orderBasketMenuOptionGroup -> {
            menuOptionBasketId.append(orderBasketMenuOptionGroup.getId());

            orderBasketMenuOptionGroup.getOrderBasketMenuOptionList().forEach(orderBasketMenuOption ->{
                menuOptionBasketId.append(orderBasketMenuOption.getId());
            });
        });

        return menuBasketId + menuOptionBasketId.toString();
    }

    /**
     * 메뉴 장바구니 적재 시 다른 가게인지 Check
     *
     * @param command
     */
    @Override
    public void orderBasketShopCheck(OrderCommand.OrderBasketRequest command) {
        List<OrderInfo.OrderBasketInfo> orderBasketList = redisCacheUtil.getMenuBasketList(command.getMemberToken());

        if (orderBasketList == null) { return; } // 해당 사용자가 장바구니 내역이 없을 경우 정상적인 flow 진행

        orderBasketList.forEach(orderBasket -> {
            if (!command.getShopToken().equals(orderBasket.getShopToken())){
                throw new InvalidParamException("another orderBasket.shopToken");
            }
        });
    }

    /**
     * 장바구니 같은 메뉴 & 옵션 확인
     *
     * @param command
     * @param hashKey
     * @return
     */
    @Override
    public OrderCommand.OrderBasketRequest duplicationMenu(OrderCommand.OrderBasketRequest command, String hashKey) {

        OrderInfo.OrderBasketInfo orderBasketInfo = redisCacheUtil.getMenuBasketHashKey(command.getMemberToken(), hashKey);

        // 동일한 메뉴, 메뉴 옵션이 존재할 경우
        if (orderBasketInfo != null){
            Long MenuOptionCount = orderBasketInfo.getOrderBasketMenu().getOrderMenuCount();
            command.getOrderBasketMenu().setOrderMenuCount(MenuOptionCount + 1);
        }

        return command;
    }

    /**
     *
     * Redis 장바구니 메뉴 조회
     *
     * @param memberToken
     * @return
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> retrieveMenuBasket(String memberToken) {
        return redisCacheUtil.getMenuBasketList(memberToken);
    }

    /**
     * Redis 장바구니 메뉴 등록
     *
     * @param hashKey
     * @param command
     */
    @Override
    public void registerCacheMenuBasket(OrderCommand.OrderBasketRequest command, String hashKey) {
        redisCacheUtil.setRedisCacheMenuBasket(command, hashKey);
    }

    /**
     * Redis 특정 메뉴 삭제
     *
     * @param memberToken
     * @param hashKey
     */
    @Override
    public void removeMenuBasket(String memberToken, String hashKey) {
        redisCacheUtil.removeMenuBasket(memberToken, hashKey);
    }

    /**
     * Redis 장바구니 메뉴 수량 변경
     *
     * @param command
     * @return
     */
    @Override
    public List<OrderInfo.OrderBasketInfo> updateMenuBasketAmount(OrderCommand.OrderBasketRequest command, String hashKey) {
        redisCacheUtil.setRedisCacheMenuBasket(command, hashKey);
        return redisCacheUtil.getMenuBasketList(command.getMemberToken());
    }

    /**
     * Redis 장바구니 전체 메뉴 삭제
     *
     * @param memberToken
     */
    @Override
    public void removeMenuBasketAll(String memberToken) {
        redisCacheUtil.removeMenuBasketAll(memberToken);
    }

    /**
     * Redis 결제 Token 정보 저장
     *
     * @param orderToken
     * @param paymentToken
     */
    @Override
    public void setRedisCacheOrderPaymentToken(String orderToken, String paymentToken) {
        redisCacheUtil.setRedisCacheOrderPaymentToken(orderToken, paymentToken);
    }

    /**
     * 주문 메뉴 및 주문 메뉴 옵션 등록
     *
     * @param order
     * @param registerOrder
     * @return
     */
    @Override
    public List<OrderMenu> store(Order order, OrderCommand.RegisterOrder registerOrder) {
        return registerOrder.getOrderMenuList().stream()
                .map(orderMenuRequest -> {
                    //  주문 메뉴 등록
                    var initOrderMenu = orderMenuRequest.toEntity(order);
                    var orderMenu = orderStore.store(initOrderMenu);

                    orderMenuRequest.getOrderMenuOptionGroupList().forEach(orderMenuOptionGroupRequest -> {

                        // 주문 메뉴 옵션 그룹 등록
                        var initOrderMenuOptionGroup = orderMenuOptionGroupRequest.toEntity(orderMenu);
                        var orderMenuOptionGroup = orderStore.store(initOrderMenuOptionGroup);

                        orderMenuOptionGroupRequest.getOrderMenuOptionList().forEach(orderMenuOptionRequest -> {

                            // 주문 메뉴 옵션 등록
                            var initOrderMenuOption = orderMenuOptionRequest.toEntity(orderMenuOptionGroup);
                            orderStore.store(initOrderMenuOption);

                        });
                    });
                    return orderMenu;
                }).collect(Collectors.toList());
    }

    /**
     * payment 정보 OrderCommand.PaymentApproveRequest 정보로 변환
     *
     * @param payment
     * @param pgToken
     * @return
     */
    @Override
    public OrderCommand.PaymentApproveRequest approveRequestConvertPayment(Payment payment, String pgToken) {

        var payMethod = PayMethod.valueOf(payment.getPaymentType());

        return OrderCommand.PaymentApproveRequest.builder()
                .orderToken(payment.getOrderToken())
                .paymentToken(payment.getPaymentToken())
                .payMethod(payMethod)
                .pgToken(pgToken)
                .build();
    }

    /**
     * OrderCommand.OrderPaymentConfirmRequest 정보 make
     *
     * @param paymentToken
     * @return
     */
    @Override
    public OrderInfo.OrderPaymentConfirmRequest orderPaymentConfirmInfo(String paymentToken) {
        var payment = paymentRead.getPayment(paymentToken);
        var order = orderRead.getOrder(payment.getOrderToken());
        var shop = shopReader.getShopByToken(order.getShopToken());
        var owner = ownerReader.getOwnerByToken(shop.getOwnerToken());

        return OrderInfo.OrderPaymentConfirmRequest.builder()
                .ownerToken(owner.getOwnerToken())
                .build();
    }

    /**
     * 주문 가격 정보 validation 확인
     *
     * @param command
     */
    @Override
    public void orderPriceValidator(OrderCommand.RegisterOrder command) {
        var discountPrice = 1L;

        //  사용한 쿠폰이 있을 경우 가격 정보 조회
        if (!StringUtils.isEmpty(command.getCouponIssueToken())){
            var response = commonApiService.CouponIssueApiRequest(command.getCouponIssueToken());
            Map<String, Object> couponInfo = (Map<String, Object>) response.getData(); // discountPrice
            discountPrice = Double.valueOf((double) couponInfo.get("discountPrice")).longValue();
        }

        // 주문 메뉴 총 가격
        var totalMenuPrice = command.getOrderMenuList().stream()
                .mapToLong(orderMenu -> {

                    var optionPrice =orderMenu.getOrderMenuOptionGroupList().stream()
                            .mapToLong(orderMenuOptionGroup -> orderMenuOptionGroup.getOrderMenuOptionList().stream()
                                    .mapToLong(orderMenuOption -> orderMenuOption.getOrderMenuOptionPrice()).sum()).sum();

                    var menuPrice = orderMenu.getOrderMenuPrice() * orderMenu.getOrderMenuCount();

                    return menuPrice + optionPrice;
                }).sum();

        var totalAmount = totalMenuPrice - discountPrice; // 주문 총 결제 금액

        if (totalAmount != command.getTotalAmount()) {
            throw new InvalidParamException("주문가격이 불일치합니다.");
        }
    }

    /**
     * 결제 승인 후 발행 한 쿠폰 정보 상태 [USED] 변경
     *
     * @param payment
     */
    @Override
    public void orderCouponIssueStatusUsed(Payment payment) {
        var order = orderRead.getOrder(payment.getOrderToken());
        if (!StringUtils.isEmpty(order.getCouponIssueToken())){
            commonApiService.CouponIssueUsedApiRequest(order.getCouponIssueToken());
        }
    }
}
