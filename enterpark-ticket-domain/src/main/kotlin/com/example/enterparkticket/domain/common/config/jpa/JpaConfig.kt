package com.example.enterparkticket.domain.common.config.jpa

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
class JpaConfig {

    @Bean
    fun securityAuditorAware(auditorAwarePort: AuditorAwarePort): AuditorAware<String> {
        return SecurityAuditorAware(auditorAwarePort)
    }
}
