package com.example.enterparkticket.domain.remainingseat.model.repository

import com.example.enterparkticket.domain.remainingseat.model.RemainingSeat
import org.springframework.data.jpa.repository.JpaRepository

interface RemainingSeatRepository : JpaRepository<RemainingSeat, Long> {

    fun findAllByPerformanceId(performanceId: Long): List<RemainingSeat>
}
