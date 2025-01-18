package com.example.enterparkticket.domain.remainingseat.adapter

import com.example.enterparkticket.domain.remainingseat.command.RemainingSeatCommandService
import com.example.enterparkticket.domain.remainingseat.model.GradeType
import com.example.enterparkticket.domain.remainingseat.query.RemainingSeatQueryService
import com.example.enterparkticket.domain.reservation.port.RemainingSeatPort
import org.springframework.stereotype.Component

@Component
class RemainingSeatPortAdapter(
    private val remainingSeatCommandService: RemainingSeatCommandService,
    private val remainingSeatQueryService: RemainingSeatQueryService,
) : RemainingSeatPort {

    override fun decreaseSeatCount(performanceId: Long, gradeType: GradeType) {
        remainingSeatQueryService.findAllByPerformanceId(performanceId)
            .filter { gradeType == it.grade }
            .forEach {
                remainingSeatCommandService.decreaseRemainingSeatCount(it)
            }
    }
}
