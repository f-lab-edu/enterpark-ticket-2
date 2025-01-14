package com.example.enterparkticket.apis.enduser.security.adapter

import com.example.enterparkticket.apis.enduser.security.dto.UserPrincipal
import com.example.enterparkticket.domain.common.config.jpa.AuditorAwarePort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuditorAwareAdapter : AuditorAwarePort {

    override fun getCurrentAuditor(): String? {
        return SecurityContextHolder.getContext().authentication
            ?.takeIf { it.isAuthenticated }
            ?.principal
            ?.let { principal ->
                if (principal is UserPrincipal) {
                    principal.userId.toString()
                } else {
                    principal.toString()
                }
            }
    }
}
