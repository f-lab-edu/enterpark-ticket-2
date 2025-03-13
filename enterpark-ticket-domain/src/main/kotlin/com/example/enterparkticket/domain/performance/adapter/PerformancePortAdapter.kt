package com.example.enterparkticket.domain.performance.adapter

import com.example.enterparkticket.domain.performance.query.PerformanceQueryService
import com.example.enterparkticket.domain.performance.validator.PerformanceValidator
import com.example.enterparkticket.domain.reservation.port.PerformancePort
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class PerformancePortAdapter(
    private val performanceQueryService: PerformanceQueryService,
    private val performanceValidator: PerformanceValidator,
) : PerformancePort {

    override fun checkUserAgeForPerformance(performanceId: Long, userAge: LocalDate) {
        val performance = performanceQueryService.findByPerformanceId(performanceId)
        performanceValidator.validateUserAge(performance, userAge)
    }
}
