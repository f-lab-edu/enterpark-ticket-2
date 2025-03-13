package com.example.enterparkticket.domain.performance.model.repository

import com.example.enterparkticket.domain.performance.model.Performance
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceRepository : JpaRepository<Performance, Long> {
}
