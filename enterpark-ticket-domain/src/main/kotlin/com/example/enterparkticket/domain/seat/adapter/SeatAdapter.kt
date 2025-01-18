package com.example.enterparkticket.domain.seat.adapter

import com.example.enterparkticket.domain.reservation.port.SeatPort
import com.example.enterparkticket.domain.seat.command.SeatCommandService
import com.example.enterparkticket.domain.seat.model.Seat
import com.example.enterparkticket.domain.seat.query.SeatQueryService
import com.example.enterparkticket.domain.seat.validator.SeatValidator
import org.springframework.stereotype.Component

@Component
class SeatAdapter(
    private val seatCommandService: SeatCommandService,
    private val seatQueryService: SeatQueryService,
    private val seatValidator: SeatValidator,
) : SeatPort {

    override fun getNotReservedSeats(performanceId: Long, seatNumbers: List<String>): List<Seat> {
        return seatQueryService.findByRemainingSeatPerformanceIdAndSeatNumberIn(
            performanceId,
            seatNumbers
        ).onEach {
            seatValidator.validateIsReserved(it)
        }
    }

    override fun updateReservedSeats(performanceId: Long, seatNumbers: List<String>) {
        seatCommandService.bulkUpdateReservedSeats(performanceId, seatNumbers)
    }
}
