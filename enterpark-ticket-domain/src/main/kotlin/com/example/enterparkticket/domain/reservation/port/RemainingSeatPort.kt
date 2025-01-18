package com.example.enterparkticket.domain.reservation.port

import com.example.enterparkticket.domain.remainingseat.model.GradeType

interface RemainingSeatPort {

    fun decreaseSeatCount(performanceId: Long, gradeType: GradeType)
}
