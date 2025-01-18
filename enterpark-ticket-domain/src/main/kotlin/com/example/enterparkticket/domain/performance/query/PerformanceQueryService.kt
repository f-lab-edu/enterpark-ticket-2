package com.example.enterparkticket.domain.performance.query

import com.example.enterparkticket.domain.performance.exception.NotFoundPerformanceException
import com.example.enterparkticket.domain.performance.model.Performance
import com.example.enterparkticket.domain.performance.repository.PerformanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PerformanceQueryService(
    private val performanceRepository: PerformanceRepository,
) {

    fun findByPerformanceId(performanceId: Long): Performance {
        return performanceRepository.findById(performanceId).orElseThrow {
            NotFoundPerformanceException()
        }
    }
}
