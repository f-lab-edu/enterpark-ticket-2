package com.example.enterparkticket.domain.remainingseat.query

import com.example.enterparkticket.domain.remainingseat.model.RemainingSeat
import com.example.enterparkticket.domain.remainingseat.repository.RemainingSeatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RemainingSeatQueryService(
    private val remainingSeatRepository: RemainingSeatRepository,
) {

    fun findAllByPerformanceId(performanceId: Long): List<RemainingSeat> {
        return remainingSeatRepository.findAllByPerformanceId(performanceId)
    }
}
