package dev.toyproject.foodDelivery;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstracEntity {

    @CreationTimestamp
    private ZonedDateTime regDate;

    @UpdateTimestamp
    private ZonedDateTime modDate;
}
