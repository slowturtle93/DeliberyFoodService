package dev.toyproject.foodDelivery.member.domain.address;

import dev.toyproject.foodDelivery.address.domain.AddressFragment;
import dev.toyproject.foodDelivery.common.exception.InvalidParamException;
import dev.toyproject.foodDelivery.common.util.TokenGenerator;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "member_address_info")
public class MemberAddress extends AbstractEntity {

    private static final String MEMBER_ADDRESS_PREFIX = "mad_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberToken;
    private String memberAddressToken;
    private String detail;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ENABLE("활성화"),
        DISABLE("비활성화"),
        DELETE("삭제");

        private final String description;
    }

    @Embedded
    @AttributeOverride(name = "region1DepthName", column = @Column(name = "region_1depth_name"))
    @AttributeOverride(name = "region2DepthName", column = @Column(name = "region_2depth_name"))
    @AttributeOverride(name = "region3DepthName", column = @Column(name = "region_3depth_name"))
    private AddressFragment addressFragment;

    @Builder
    public MemberAddress(
            String memberToken,
            String detail,
            AddressFragment addressFragment
    ){
        if(StringUtils.isEmpty(memberToken)) throw new InvalidParamException("MemberAddress.memberToken");
        if(addressFragment == null)          throw new InvalidParamException("MemberAddress.addressFragment");

        this.memberAddressToken = TokenGenerator.randomCharacterWithPrefix(MEMBER_ADDRESS_PREFIX);
        this.memberToken        = memberToken;
        this.detail             = detail;
        this.addressFragment    = addressFragment;
        this.status             = Status.ENABLE;
    }

    // 상세주소 수정
    public void updateAddress(String detail){ this.detail = detail; }

    // 상태 [ENABLE] 변경
    public void enable() { this.status = Status.ENABLE; }

    // 상태 [DISABLE] 변경
    public void disable() { this.status = Status.DISABLE; }

    // 상태 [DELETE] 변경
    public void delete() { this.status = Status.DELETE; }
}
