package com.example.enterparkticket.domain.config.jpa

import org.springframework.data.domain.AuditorAware
import java.util.*

class SecurityAuditorAware(private val auditorAwarePort: AuditorAwarePort) : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable(auditorAwarePort.getCurrentAuditor())
    }
}
