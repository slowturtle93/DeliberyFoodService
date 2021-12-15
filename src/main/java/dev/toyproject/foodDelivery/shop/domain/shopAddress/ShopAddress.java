package dev.toyproject.foodDelivery.shop.domain.shopAddress;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.shop.domain.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "shop_building_info")
public class ShopAddress extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detail;

    @Embedded
    @AttributeOverride(name = "region1DepthName", column = @Column(name = "region_1depth_name"))
    @AttributeOverride(name = "region2DepthName", column = @Column(name = "region_2depth_name"))
    @AttributeOverride(name = "region3DepthName", column = @Column(name = "region_3depth_name"))
    private AddressFragment addressFragment;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Builder
    public ShopAddress(
            Shop shop,
            String detail,
            AddressFragment addressFragment
    ){
        if(shop == null)            throw new InvalidParamException("OwnerAddress.shop");
        if(addressFragment == null) throw new InvalidParamException("OwnerAddress.addressFragment");

        this.shop                  = shop;
        this.detail                = detail;
        this.addressFragment       = addressFragment;
    }

    // 주소 변경
    public void updateAddress(AddressFragment addressFragment, String detail){
        this.addressFragment = addressFragment;
        this.detail          = detail;
    }

}
