package dev.toyproject.foodDelivery.shop.infrastructure;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.toyproject.foodDelivery.shop.domain.QShop;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import dev.toyproject.foodDelivery.shop.domain.ShopCommand;
import dev.toyproject.foodDelivery.shop.domain.shopAddress.QShopAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.dsl.MathExpressions.*;

@Slf4j
@Repository
public class ShopRepositorySupportImpl extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    private static int RADIUS_2_KM = 2;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param queryFactory must not be {@literal null}.
     */
    public ShopRepositorySupportImpl(JPAQueryFactory queryFactory) {
        super(Shop.class);
        this.queryFactory = queryFactory;
    }

    /**
     * 반경 2km 이내의 가게 조회
     *
     * @param request
     * @return
     */
    public List<Shop> findSearchShop(ShopCommand.MemberLocationRequest request){
        QShop shop = QShop.shop;
        QShopAddress shopAddress = QShopAddress.shopAddress;

        // 반경 거리 계산 Expression
        NumberExpression<Double> distanceExpression =
                acos(cos(radians(Expressions.constant(request.getX())))
                        .multiply(cos(radians(shopAddress.addressFragment.x)))
                        .multiply(cos(radians(shopAddress.addressFragment.y)
                                .subtract(radians(Expressions.constant(request.getY())))))
                        .add(sin(radians(Expressions.constant(request.getX())))
                                .multiply(sin(radians(shopAddress.addressFragment.x)))))
                        .multiply(Expressions.constant(6371));

        return queryFactory
                .select(shop)
                .from(shop)
                .where(shop.id.eq(
                        queryFactory
                                .select(shopAddress.shop.id)
                                .from(shopAddress)
                                .where(distanceExpression.lt(RADIUS_2_KM))
                                .orderBy(distanceExpression.desc())
                ).and(shop.status.eq(Shop.Status.ENABLE)))
                .fetch();
    }
}
