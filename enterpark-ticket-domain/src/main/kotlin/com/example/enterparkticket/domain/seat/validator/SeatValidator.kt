package com.example.enterparkticket.domain.seat.validator

import com.example.enterparkticket.domain.seat.exception.AlreadyReservedSeatException
import com.example.enterparkticket.domain.seat.model.Seat
import org.springframework.stereotype.Component

@Component
class SeatValidator {

    fun validateIsReserved(seat: Seat) {
        if (seat.isReserved) {
            throw AlreadyReservedSeatException()
        }
    }
}
