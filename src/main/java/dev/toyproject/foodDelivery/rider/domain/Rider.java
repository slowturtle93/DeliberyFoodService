package dev.toyproject.foodDelivery.rider.domain;

import dev.toyproject.foodDelivery.AbstracEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "rider")
public class Rider extends AbstracEntity {

}
