package dev.toyproject.foodDelivery.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @EnableJpaAuditing 어노테이션을 적용하여 JPA Auditing 을 활성화
 *
 * Auditing
 * Spring Data JPA 에서 시간에 대해서 자동으로 값을 넣어주는 기능
 */
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfiguration {
}
