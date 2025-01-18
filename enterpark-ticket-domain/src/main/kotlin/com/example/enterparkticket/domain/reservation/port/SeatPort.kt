package com.example.enterparkticket.domain.reservation.port

import com.example.enterparkticket.domain.seat.model.Seat

interface SeatPort {

    fun getNotReservedSeats(performanceId: Long, seatNumbers: List<String>): List<Seat>

    fun updateReservedSeats(performanceId: Long, seatNumbers: List<String>)
}
