package com.example.enterparkticket.domain.common.config.jpa

interface AuditorAwarePort {

    fun getCurrentAuditor(): String?
}
