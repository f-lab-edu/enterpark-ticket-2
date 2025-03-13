package com.example.enterparkticket.domain.config.jpa

interface AuditorAwarePort {

    fun getCurrentAuditor(): String?
}
