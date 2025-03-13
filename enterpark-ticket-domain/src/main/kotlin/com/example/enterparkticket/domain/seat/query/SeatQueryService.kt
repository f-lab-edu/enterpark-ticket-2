package com.example.enterparkticket.domain.seat.query

import com.example.enterparkticket.domain.seat.model.Seat
import com.example.enterparkticket.domain.seat.model.repository.SeatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SeatQueryService(
    private val seatRepository: SeatRepository,
) {

    fun findByRemainingSeatPerformanceIdAndSeatNumberIn(
        performanceId: Long,
        seatNumbers: List<String>,
    ): List<Seat> {
        return seatRepository.findByRemainingSeatPerformanceIdAndSeatNumberIn(
            performanceId,
            seatNumbers,
        )
    }
}
