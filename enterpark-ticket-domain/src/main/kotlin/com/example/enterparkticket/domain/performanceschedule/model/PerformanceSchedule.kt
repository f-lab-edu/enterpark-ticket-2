package com.example.enterparkticket.domain.performanceschedule.model

import com.example.enterparkticket.domain.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class PerformanceSchedule(
    schedule: LocalDateTime,
    sequence: Int,
    performanceId: Long,
    castingId: Long,
    remainingSeatId: Long,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_schedule_id")
    var id: Long? = null
        protected set

    @Column(nullable = false)
    var schedule: LocalDateTime = schedule
        protected set

    @Column(nullable = false)
    var sequence: Int = sequence
        protected set

    @Column(nullable = false)
    var performanceId: Long = performanceId
        protected set

    @Column(nullable = false)
    var castingId: Long = castingId
        protected set

    @Column(nullable = false)
    var remainingSeatId: Long = remainingSeatId
        protected set
}
