package com.example.enterparkticket.domain.reservation.event

import com.example.enterparkticket.domain.reservation.model.Reservation

data class ReservationWaitingPaymentEvent(
    val reservations: List<Reservation>,
)
