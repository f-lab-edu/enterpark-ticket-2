package com.example.enterparkticket.domain.seat.command

import com.example.enterparkticket.domain.seat.model.repository.SeatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SeatCommandService(
    private val seatRepository: SeatRepository,
) {

    fun bulkUpdateReservedSeats(performanceId: Long, seatNumbers: List<String>) {
        seatRepository.bulkUpdateReservedSeats(performanceId, seatNumbers)
    }
}
