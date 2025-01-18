package com.example.enterparkticket.domain.reservation.port

import java.time.LocalDate

interface PerformancePort {

    fun checkUserAgeForPerformance(performanceId: Long, userAge: LocalDate)
}
