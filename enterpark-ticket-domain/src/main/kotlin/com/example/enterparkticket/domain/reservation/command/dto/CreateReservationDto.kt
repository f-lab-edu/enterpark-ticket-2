package com.example.enterparkticket.domain.reservation.command.dto

import com.example.enterparkticket.domain.remainingseat.model.GradeType
import com.example.enterparkticket.domain.reservation.model.Reservation
import com.example.enterparkticket.domain.reservation.model.TicketReceiptType

data class CreateReservationDto(
    val performanceId: Long,
    val seats: List<CreateReservationSeatDto>,
    val ticketReceiptType: TicketReceiptType,
) {

    fun toReservationEntity(userId: Long, seatId: Long?): Reservation {
        return Reservation(
            userId,
            performanceId,
            seatId,
            ticketReceiptType,
        )
    }

    fun toSeatNumbers(): List<String> {
        return seats.map { it.number }
    }
}

data class CreateReservationSeatDto(
    val gradeType: GradeType,
    val number: String,
)
